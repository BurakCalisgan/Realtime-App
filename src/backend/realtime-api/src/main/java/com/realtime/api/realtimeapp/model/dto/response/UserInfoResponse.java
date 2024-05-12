package com.realtime.api.realtimeapp.model.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponse {
    private long id;
    private String username;
    private String email;
    private List<String> roles;
}
