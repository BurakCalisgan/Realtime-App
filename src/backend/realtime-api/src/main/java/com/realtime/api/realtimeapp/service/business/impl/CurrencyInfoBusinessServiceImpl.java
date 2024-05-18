package com.realtime.api.realtimeapp.service.business.impl;

import com.realtime.api.realtimeapp.mapper.CurrencyInfoMapper;
import com.realtime.api.realtimeapp.model.dto.response.CurrencyInfoResponse;
import com.realtime.api.realtimeapp.service.business.CurrencyInfoBusinessService;
import com.realtime.api.realtimeapp.service.domain.CurrencyInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyInfoBusinessServiceImpl implements CurrencyInfoBusinessService {

    private final CurrencyInfoService currencyInfoService;
    private final CurrencyInfoMapper currencyInfoMapper;

    @Override
    public List<CurrencyInfoResponse> getCurrencies() {
        log.info("Retrieving all symbols");
        return currencyInfoMapper.toDto(currencyInfoService.getCurrencies());
    }

    @Override
    @Cacheable(cacheNames = "currencyinfo", key = "#currency")
    public CurrencyInfoResponse getCurrencyInfoByCurrency(String currency) {
        return currencyInfoMapper.toDto(currencyInfoService.getCurrencyInfoByCurrency(currency));
    }

}
