package com.realtime.api.realtimeapp.repository;


import com.realtime.api.realtimeapp.entity.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymbolRepository extends JpaRepository<Symbol, Long> {
    Symbol findById(long id);

}
