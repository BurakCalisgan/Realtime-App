package com.realtime.api.realtimeapp.exception.general;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ValidationError {

    @Schema(description = "Error field", example = "name")
    private String field;

    @Schema(description = "Error name", example = "NotNull")
    private String error;

    @Schema(description = "Error description", example = "name must not be null")
    private String message;
}
