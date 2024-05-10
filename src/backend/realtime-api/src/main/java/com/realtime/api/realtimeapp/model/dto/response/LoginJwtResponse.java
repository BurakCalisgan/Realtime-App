package com.realtime.api.realtimeapp.model.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginJwtResponse {
    private String token;
    private Long id;
    private String email;
    private List<String> roles;
}
