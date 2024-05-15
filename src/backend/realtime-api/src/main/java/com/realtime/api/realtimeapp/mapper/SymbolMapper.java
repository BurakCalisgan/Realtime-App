package com.realtime.api.realtimeapp.mapper;

import com.realtime.api.realtimeapp.entity.Symbol;
import com.realtime.api.realtimeapp.model.dto.request.SymbolUpdateRequestDto;
import com.realtime.api.realtimeapp.model.dto.response.SymbolResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SymbolMapper {
    List<SymbolResponse> toDto(List<Symbol> symbols);

    Symbol toEntity(SymbolUpdateRequestDto symbolUpdateRequestDto);

}
