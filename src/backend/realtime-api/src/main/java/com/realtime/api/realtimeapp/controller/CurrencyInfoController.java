package com.realtime.api.realtimeapp.controller;

import com.realtime.api.realtimeapp.model.dto.response.CurrencyInfoResponse;
import com.realtime.api.realtimeapp.service.business.CurrencyInfoBusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/currency-info")
@RequiredArgsConstructor
@Slf4j
public class CurrencyInfoController {

    private final CurrencyInfoBusinessService currencyInfoBusinessService;

    @GetMapping("/all-currencies")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<CurrencyInfoResponse>> getAllCurrencies() {
        return new ResponseEntity<>(currencyInfoBusinessService.getCurrencies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<CurrencyInfoResponse> getCurrencyInfoById(@PathVariable Long id) {
        return new ResponseEntity<>(currencyInfoBusinessService.getCurrencyInfoById(id), HttpStatus.OK);
    }


}
