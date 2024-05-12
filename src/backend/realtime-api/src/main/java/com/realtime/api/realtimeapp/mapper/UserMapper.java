package com.realtime.api.realtimeapp.mapper;

import com.realtime.api.realtimeapp.entity.User;
import com.realtime.api.realtimeapp.model.dto.request.UserLoginRequestDto;
import com.realtime.api.realtimeapp.model.dto.request.UserRegisterRequestDto;
import com.realtime.api.realtimeapp.model.dto.response.UserInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    User toEntity(UserRegisterRequestDto userRegisterRequestDto);

    @Mapping(target = "roles", ignore = true)
    UserInfoResponse toDto(User user);

    UserLoginRequestDto toRequestDto(User user);

}
