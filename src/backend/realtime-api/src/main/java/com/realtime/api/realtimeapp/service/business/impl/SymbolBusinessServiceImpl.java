package com.realtime.api.realtimeapp.service.business.impl;

import com.realtime.api.realtimeapp.entity.Symbol;
import com.realtime.api.realtimeapp.mapper.SymbolMapper;
import com.realtime.api.realtimeapp.model.dto.request.SymbolUpdateRequestDto;
import com.realtime.api.realtimeapp.model.dto.response.MessageResponse;
import com.realtime.api.realtimeapp.model.dto.response.SymbolResponse;
import com.realtime.api.realtimeapp.service.business.SymbolBusinessService;
import com.realtime.api.realtimeapp.service.domain.SymbolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SymbolBusinessServiceImpl implements SymbolBusinessService {

    private final SymbolService symbolService;
    private final SymbolMapper symbolMapper;

    @Override
    public List<SymbolResponse> getAllSymbols() {
        log.info("Retrieving all symbols");
        List<Symbol> symbols = symbolService.getAllSymbols();

        log.info("Retrieved all symbols end");
        return symbolMapper.toDto(symbols);
    }

    @Override
    public MessageResponse updateSymbol(long id, SymbolUpdateRequestDto requestDto) {
        log.info("Updating symbol with id {}", id);

        Symbol symbol = symbolMapper.toEntity(requestDto);

        symbolService.updateSymbol(symbol);
        log.info("Updating symbol end with id {}", id);
        return new MessageResponse("Updated symbol with id " + id);

    }
}
