package com.realtime.api.realtimeapp.service.domain.impl;


import com.realtime.api.realtimeapp.entity.Symbol;
import com.realtime.api.realtimeapp.repository.SymbolRepository;
import com.realtime.api.realtimeapp.service.domain.SymbolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SymbolServiceImpl implements SymbolService {
    private final SymbolRepository repository;

    @Override
    public List<Symbol> getAllSymbols() {
        return repository.findAll();
    }

    @Override
    public Symbol getSymbolById(long id) {
        return repository.findById(id);
    }

    @Override
    public void updateSymbol(Symbol symbol) {
        repository.save(symbol);
    }
}
