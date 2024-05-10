package com.realtime.api.realtimeapp.service.business.impl;

import com.realtime.api.realtimeapp.mapper.UserMapper;
import com.realtime.api.realtimeapp.model.dto.response.UserInfoResponse;
import com.realtime.api.realtimeapp.service.business.UserBusinessService;
import com.realtime.api.realtimeapp.service.domain.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserBusinessServiceImpl implements UserBusinessService {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserInfoResponse getUserInfo(Long userId) {
        log.info("Requesting user info for user id {}", userId);
        return userMapper.toDto(userService.findById(userId));
    }
}
