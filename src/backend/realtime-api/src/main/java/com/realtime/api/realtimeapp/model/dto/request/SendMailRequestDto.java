package com.realtime.api.realtimeapp.model.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendMailRequestDto {
    private String to;
    private String subject;
    private String text;
}