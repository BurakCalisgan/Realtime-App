package com.realtime.api.realtimeapp.model.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SymbolResponse {
    private Long id;
    private String symbol;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
}
