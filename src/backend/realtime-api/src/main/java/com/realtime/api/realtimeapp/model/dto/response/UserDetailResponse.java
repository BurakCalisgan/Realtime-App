package com.realtime.api.realtimeapp.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailResponse {
    private long id;
    private String username;
    private String email;
    private String password;
    private List<String> roles;
}
