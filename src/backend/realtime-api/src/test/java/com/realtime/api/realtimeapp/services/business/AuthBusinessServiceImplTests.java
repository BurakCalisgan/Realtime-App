package com.realtime.api.realtimeapp.services.business;

import com.realtime.api.realtimeapp.entity.Role;
import com.realtime.api.realtimeapp.entity.User;
import com.realtime.api.realtimeapp.exception.UserAlreadyExistsException;
import com.realtime.api.realtimeapp.mapper.UserMapper;
import com.realtime.api.realtimeapp.model.dto.request.UserLoginRequestDto;
import com.realtime.api.realtimeapp.model.dto.request.UserRegisterRequestDto;
import com.realtime.api.realtimeapp.model.dto.response.LoginJwtResponse;
import com.realtime.api.realtimeapp.model.enums.RoleEnum;
import com.realtime.api.realtimeapp.security.UserDetailsImpl;
import com.realtime.api.realtimeapp.service.business.impl.AuthBusinessServiceImpl;
import com.realtime.api.realtimeapp.service.domain.RoleService;
import com.realtime.api.realtimeapp.service.domain.UserService;
import com.realtime.api.realtimeapp.util.JwtUtils;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthBusinessServiceImplTests {

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private AuthBusinessServiceImpl authBusinessService;

    @Test
    void registerUser_WithValidRequest_ShouldRegisterUserSuccessfully() {
        // Arrange
        UserRegisterRequestDto requestDto = new UserRegisterRequestDto();
        requestDto.setUsername("testuser");
        requestDto.setEmail("test@example.com");
        requestDto.setPassword("password");
        requestDto.setRole(new HashSet<>());

        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");


        Role userRole = new Role(1L, RoleEnum.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        when(roleService.findByName(any())).thenReturn(Optional.of(userRole));
        when(userService.existsByEmail(any())).thenReturn(false);
        when(userService.existsByUsername(any())).thenReturn(false);
        doNothing().when(userService).createUser(any());
        when(userMapper.toEntity(requestDto)).thenReturn(user);
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");

        // Act
        var response = authBusinessService.registerUser(requestDto);
        //Assert
        assertNotNull(response);
    }

    @Test
    void registerUser_WithExistingUsername_ShouldThrowUserAlreadyExistsException() {
        // Arrange
        UserRegisterRequestDto requestDto = new UserRegisterRequestDto();
        requestDto.setUsername("existinguser");
        requestDto.setEmail("test@example.com");
        requestDto.setPassword("password");
        requestDto.setRole(new HashSet<>());

        when(userService.existsByUsername(requestDto.getUsername())).thenReturn(true);

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> authBusinessService.registerUser(requestDto));
    }

    @Test
    void registerUser_WithExistingEmail_ShouldThrowUserAlreadyExistsException() {
        // Arrange
        UserRegisterRequestDto requestDto = new UserRegisterRequestDto();
        requestDto.setUsername("testuser");
        requestDto.setEmail("existing@example.com");
        requestDto.setPassword("password");
        requestDto.setRole(new HashSet<>());

        when(userService.existsByEmail(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> authBusinessService.registerUser(requestDto));
    }

    @Test
    void login_WithValidCredentials_ShouldReturnJwtResponse() {
        // Arrange
        UserLoginRequestDto requestDto = new UserLoginRequestDto();
        requestDto.setEmail("test@example.com");
        requestDto.setPassword("password");

        UserDetailsImpl userDetails = new UserDetailsImpl(1L, "test","test@example.com",
                "password", List.of(new SimpleGrantedAuthority("ROLE_USER")));

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        List<String> roles = List.of("ROLE_USER");
        String jwt = "mocked.jwt.token";

        // Mocking
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtUtils.generateTokenWithClaims(any())).thenReturn(jwt);

        // Act
        LoginJwtResponse response = authBusinessService.login(requestDto);

        // Assert
        assertEquals(userDetails.getId(), response.getId());
        assertEquals(userDetails.getEmail(), response.getEmail());
        assertEquals(jwt, response.getToken());
        assertEquals(roles, response.getRoles());

        verify(authenticationManager, times(1)).authenticate(any());
        verify(jwtUtils, times(1)).generateTokenWithClaims(any());
    }
}