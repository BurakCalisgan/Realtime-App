package com.realtime.api.realtimeapp.service.domain;


import com.realtime.api.realtimeapp.entity.Symbol;

import java.util.List;

public interface SymbolService {
    List<Symbol> getAllSymbols();
    Symbol getSymbolById(long id);
    void updateSymbol(Symbol symbol);
}
