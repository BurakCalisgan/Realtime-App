package com.realtime.api.realtimeapp.controller;

import com.realtime.api.realtimeapp.model.dto.request.UserLoginRequestDto;
import com.realtime.api.realtimeapp.model.dto.request.UserRegisterRequestDto;
import com.realtime.api.realtimeapp.model.dto.response.LoginJwtResponse;
import com.realtime.api.realtimeapp.model.dto.response.MessageResponse;
import com.realtime.api.realtimeapp.service.business.AuthBusinessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthBusinessService authBusinessService;

    @PostMapping("/register")
    private ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        return new ResponseEntity<>(authBusinessService.registerUser(userRegisterRequestDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    private ResponseEntity<LoginJwtResponse> login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
        return new ResponseEntity<>(authBusinessService.login(userLoginRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("logout/{username}/{userId}")
    private ResponseEntity<MessageResponse> logout(@PathVariable String username, @PathVariable long userId) {
        return new ResponseEntity<>(authBusinessService.logout(username, userId), HttpStatus.OK);
    }
}
