package com.realtime.api.realtimeapp.service.domain;


import com.realtime.api.realtimeapp.entity.CurrencyInfo;

import java.util.List;

public interface CurrencyInfoService {
    List<CurrencyInfo> getCurrencies();
    CurrencyInfo getCurrencyInfoById(long id);
}
