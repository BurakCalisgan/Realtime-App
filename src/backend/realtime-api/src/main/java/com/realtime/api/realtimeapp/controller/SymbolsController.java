package com.realtime.api.realtimeapp.controller;

import com.realtime.api.realtimeapp.model.dto.request.SymbolUpdateRequestDto;
import com.realtime.api.realtimeapp.model.dto.response.SymbolResponse;
import com.realtime.api.realtimeapp.service.business.SymbolBusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/symbol")
@RequiredArgsConstructor
@Slf4j
public class SymbolsController {

    private final SymbolBusinessService symbolBusinessService;

    @GetMapping("/all-symbols")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<SymbolResponse>> getAllSymbols() {
        return new ResponseEntity<>(symbolBusinessService.getAllSymbols(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void updateSymbol(@PathVariable Long id, @RequestBody SymbolUpdateRequestDto requestDto){
        symbolBusinessService.updateSymbol(id, requestDto);
    }



}
