package com.realtime.api.realtimeapp.exception.general;

import lombok.Data;

import java.util.List;

@Data
public class ApiError {
    private String code;
    private String message;
    private Long timestamp;
    private String status;
    private List<ValidationError> details;
}
