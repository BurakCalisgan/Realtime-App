package com.realtime.api.realtimeapp.security;

import com.realtime.api.realtimeapp.model.dto.response.UserDetailResponse;
import com.realtime.api.realtimeapp.service.business.UserBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserBusinessService userBusinessService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailResponse user = userBusinessService.getUserDetailsByEmail(username);

        return UserDetailsImpl.build(user);
    }


}
