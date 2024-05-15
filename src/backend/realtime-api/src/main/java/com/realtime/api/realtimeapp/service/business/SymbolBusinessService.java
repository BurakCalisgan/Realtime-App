package com.realtime.api.realtimeapp.service.business;


import com.realtime.api.realtimeapp.model.dto.request.SymbolUpdateRequestDto;
import com.realtime.api.realtimeapp.model.dto.response.SymbolResponse;

import java.util.List;

public interface SymbolBusinessService {
    List<SymbolResponse> getAllSymbols();
    void updateSymbol(long id, SymbolUpdateRequestDto requestDto);
}
