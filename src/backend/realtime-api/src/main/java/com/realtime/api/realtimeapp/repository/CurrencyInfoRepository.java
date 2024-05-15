package com.realtime.api.realtimeapp.repository;


import com.realtime.api.realtimeapp.entity.CurrencyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyInfoRepository extends JpaRepository<CurrencyInfo, Long> {
    CurrencyInfo findByCurrency(String currency);

}
