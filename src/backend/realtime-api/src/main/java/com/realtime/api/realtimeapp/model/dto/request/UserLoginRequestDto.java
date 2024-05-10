package com.realtime.api.realtimeapp.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
