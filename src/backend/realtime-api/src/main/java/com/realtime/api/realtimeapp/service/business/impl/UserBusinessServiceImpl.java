package com.realtime.api.realtimeapp.service.business.impl;

import com.realtime.api.realtimeapp.mapper.UserMapper;
import com.realtime.api.realtimeapp.model.dto.response.UserInfoResponse;
import com.realtime.api.realtimeapp.service.business.UserBusinessService;
import com.realtime.api.realtimeapp.service.domain.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBusinessServiceImpl implements UserBusinessService {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserInfoResponse getUserInfo(Long userId) {
        return userMapper.toDto(userService.findById(userId));
    }
}
