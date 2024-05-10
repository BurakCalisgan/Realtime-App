package com.realtime.api.realtimeapp.controller;

import com.realtime.api.realtimeapp.model.dto.response.UserInfoResponse;
import com.realtime.api.realtimeapp.service.business.UserBusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserBusinessService userBusinessService;

    @GetMapping("/user-info/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable Long userId) {
        return new ResponseEntity<>(userBusinessService.getUserInfo(userId), HttpStatus.OK);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Admin Area";
    }



}
