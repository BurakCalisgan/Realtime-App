package com.realtime.api.realtimeapp.service.domain.impl;

import com.realtime.api.realtimeapp.entity.RefreshToken;
import com.realtime.api.realtimeapp.entity.User;
import com.realtime.api.realtimeapp.repository.RefreshTokenRepository;
import com.realtime.api.realtimeapp.service.domain.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository repository;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return repository.findByToken(token);
    }

    @Override
    public int deleteByUser(User user) {
        return repository.deleteByUser(user);
    }
}
