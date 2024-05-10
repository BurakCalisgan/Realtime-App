package com.realtime.api.realtimeapp.service.domain.impl;

import com.realtime.api.realtimeapp.entity.User;
import com.realtime.api.realtimeapp.repository.UserRepository;
import com.realtime.api.realtimeapp.service.domain.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User findById(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User Not Found with userId: " + userId));
    }

    @Override
    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public void createUser(User user) {
        repository.save(user);
    }
}
