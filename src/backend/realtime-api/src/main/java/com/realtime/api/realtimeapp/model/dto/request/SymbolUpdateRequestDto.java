package com.realtime.api.realtimeapp.model.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SymbolUpdateRequestDto {
    private String symbol;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
}
