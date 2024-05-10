package com.realtime.api.realtimeapp.service.domain;

import com.realtime.api.realtimeapp.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String username);

    User findById(Long userId);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    void createUser(User user);
}
