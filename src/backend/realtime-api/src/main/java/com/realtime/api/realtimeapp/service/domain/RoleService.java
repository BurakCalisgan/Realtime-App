package com.realtime.api.realtimeapp.service.domain;


import com.realtime.api.realtimeapp.entity.Role;
import com.realtime.api.realtimeapp.model.enums.RoleEnum;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleEnum name);
}
