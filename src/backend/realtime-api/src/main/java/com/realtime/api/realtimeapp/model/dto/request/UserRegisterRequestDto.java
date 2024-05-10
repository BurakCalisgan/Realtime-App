package com.realtime.api.realtimeapp.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class UserRegisterRequestDto {
    @NotBlank
    @Size(min = 5, max = 40)
    private String username;
    @NotBlank
    @Size(max = 100)
    private String email;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    @Size(max = 120)
    private Set<String> role;
}
