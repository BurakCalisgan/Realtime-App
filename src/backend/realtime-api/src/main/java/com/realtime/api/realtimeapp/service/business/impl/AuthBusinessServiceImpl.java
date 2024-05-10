package com.realtime.api.realtimeapp.service.business.impl;

import com.realtime.api.realtimeapp.entity.Role;
import com.realtime.api.realtimeapp.entity.User;
import com.realtime.api.realtimeapp.exception.UserAlreadyExistsException;
import com.realtime.api.realtimeapp.mapper.UserMapper;
import com.realtime.api.realtimeapp.model.dto.request.UserLoginRequestDto;
import com.realtime.api.realtimeapp.model.dto.request.UserRegisterRequestDto;
import com.realtime.api.realtimeapp.model.dto.response.LoginJwtResponse;
import com.realtime.api.realtimeapp.model.dto.response.MessageResponse;
import com.realtime.api.realtimeapp.model.enums.RoleEnum;
import com.realtime.api.realtimeapp.security.UserDetailsImpl;
import com.realtime.api.realtimeapp.service.business.AuthBusinessService;
import com.realtime.api.realtimeapp.service.domain.RoleService;
import com.realtime.api.realtimeapp.service.domain.UserService;
import com.realtime.api.realtimeapp.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthBusinessServiceImpl implements AuthBusinessService {

    private final UserMapper userMapper;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public MessageResponse registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        log.info("Registering user: {}", userRegisterRequestDto);
        validateUser(userRegisterRequestDto);

        User user = userMapper.toEntity(userRegisterRequestDto);
        user.setPassword(passwordEncoder.encode(userRegisterRequestDto.getPassword()));

        Set<String> strRoles = userRegisterRequestDto.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.findByName(RoleEnum.ROLE_USER)
                    .orElseThrow(() -> new NotFoundException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleService.findByName(RoleEnum.ROLE_ADMIN)
                                .orElseThrow(() -> new NotFoundException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        Role modRole = roleService.findByName(RoleEnum.ROLE_MODERATOR)
                                .orElseThrow(() -> new NotFoundException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleService.findByName(RoleEnum.ROLE_USER)
                                .orElseThrow(() -> new NotFoundException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }

        user.setRoles(roles);
        userService.createUser(user);
        log.info("Registering transaction end. : {}", user.getEmail());

        return new MessageResponse("User registered successfully!");
    }

    @Override
    public void validateUser(UserRegisterRequestDto userRegisterRequestDto) {
        if (userService.existsByUsername(userRegisterRequestDto.getUsername())) {
            throw new UserAlreadyExistsException("Username is already taken!");
        }

        if (userService.existsByEmail(userRegisterRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("Email is already in use!");
        }
    }

    @Override
    public LoginJwtResponse login(UserLoginRequestDto userLoginRequestDto) {
        log.info("Login request: {}", userLoginRequestDto);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        userLoginRequestDto.getEmail(),
                        userLoginRequestDto.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String jwt = jwtUtils.generateTokenWithClaims(userDetails);
        log.info("Login request end: {}", jwt);

        return LoginJwtResponse.builder()
                .id(userDetails.getId())
                .email(userDetails.getEmail())
                .token(jwt)
                .roles(roles)
                .build();
    }
}
