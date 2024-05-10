package com.realtime.api.realtimeapp.service.business;

import com.realtime.api.realtimeapp.model.dto.request.UserLoginRequestDto;
import com.realtime.api.realtimeapp.model.dto.request.UserRegisterRequestDto;
import com.realtime.api.realtimeapp.model.dto.response.LoginJwtResponse;
import com.realtime.api.realtimeapp.model.dto.response.MessageResponse;

public interface AuthBusinessService {
    MessageResponse registerUser(UserRegisterRequestDto userRegisterRequestDto);

    void validateUser(UserRegisterRequestDto userRegisterRequestDto);

    LoginJwtResponse login(UserLoginRequestDto userLoginRequestDto);
}
