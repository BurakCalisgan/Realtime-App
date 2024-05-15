package com.realtime.api.realtimeapp.service.domain.impl;


import com.realtime.api.realtimeapp.entity.CurrencyInfo;
import com.realtime.api.realtimeapp.repository.CurrencyInfoRepository;
import com.realtime.api.realtimeapp.service.domain.CurrencyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyInfoServiceImpl implements CurrencyInfoService {
    private final CurrencyInfoRepository repository;

    @Override
    public List<CurrencyInfo> getCurrencies() {
        return repository.findAll();
    }

    @Override
    public CurrencyInfo getCurrencyInfoByCurrency(String currency) {
        return repository.findByCurrency(currency);
    }
}
