package com.realtime.api.realtimeapp.service.business;


import com.realtime.api.realtimeapp.model.dto.response.CurrencyInfoResponse;

import java.util.List;

public interface CurrencyInfoBusinessService {
    List<CurrencyInfoResponse> getCurrencies();
    CurrencyInfoResponse getCurrencyInfoByCurrency(String currency);
}
