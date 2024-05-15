package com.realtime.api.realtimeapp.mapper;

import com.realtime.api.realtimeapp.entity.CurrencyInfo;
import com.realtime.api.realtimeapp.model.dto.response.CurrencyInfoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CurrencyInfoMapper {
    List<CurrencyInfoResponse> toDto(List<CurrencyInfo> currencyInfos);

    CurrencyInfoResponse toDto(CurrencyInfo currencyInfo);

}
