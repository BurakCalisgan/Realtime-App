package com.realtime.api.realtimeapp.model.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyInfoResponse {
    private Long id;
    private String currency;
    private Long dailyTransaction;
    private BigDecimal dailyTradingVolume;
    private BigDecimal hourlyTradingVolume;
    private String description;
}
