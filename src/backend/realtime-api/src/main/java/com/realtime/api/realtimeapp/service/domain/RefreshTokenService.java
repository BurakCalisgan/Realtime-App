package com.realtime.api.realtimeapp.service.domain;


import com.realtime.api.realtimeapp.entity.RefreshToken;
import com.realtime.api.realtimeapp.entity.User;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);

    int deleteByUser(User user);
}
