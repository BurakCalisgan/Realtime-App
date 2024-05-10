package com.realtime.api.realtimeapp.service.domain.impl;


import com.realtime.api.realtimeapp.entity.Role;
import com.realtime.api.realtimeapp.model.enums.RoleEnum;
import com.realtime.api.realtimeapp.repository.RoleRepository;
import com.realtime.api.realtimeapp.service.domain.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Optional<Role> findByName(RoleEnum name) {
        return repository.findByName(name);
    }
}
