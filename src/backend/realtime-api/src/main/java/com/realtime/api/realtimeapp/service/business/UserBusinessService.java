package com.realtime.api.realtimeapp.service.business;


import com.realtime.api.realtimeapp.model.dto.response.UserInfoResponse;

public interface UserBusinessService {
    UserInfoResponse getUserInfo(Long userId);
}
