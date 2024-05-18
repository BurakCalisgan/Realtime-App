package com.realtime.api.realtimeapp.service.domain;

import com.realtime.api.realtimeapp.entity.User;

public interface UserService {

    User findByEmail(String username);

    User findById(Long userId);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    void createUser(User user);
}
