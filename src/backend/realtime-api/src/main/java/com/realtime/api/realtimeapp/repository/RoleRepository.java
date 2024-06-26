package com.realtime.api.realtimeapp.repository;


import com.realtime.api.realtimeapp.entity.Role;
import com.realtime.api.realtimeapp.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);

}
