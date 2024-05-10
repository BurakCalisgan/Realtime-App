package com.realtime.api.realtimeapp.services.business;

import com.realtime.api.realtimeapp.entity.User;
import com.realtime.api.realtimeapp.mapper.UserMapper;
import com.realtime.api.realtimeapp.model.dto.response.UserInfoResponse;
import com.realtime.api.realtimeapp.service.business.impl.UserBusinessServiceImpl;
import com.realtime.api.realtimeapp.service.domain.UserService;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserBusinessServiceImplTests {

    @Mock
    private UserService userService;

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @InjectMocks
    private UserBusinessServiceImpl userBusinessService;

    @Test
    public void testGetUserInfo() {
        // Arrange
        User user = User
                .builder()
                .id(1L)
                .email("test@mail.com")
                .username("test")
                .build();
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        userInfoResponse.setId(user.getId());
        userInfoResponse.setEmail(user.getEmail());
        userInfoResponse.setUsername(user.getUsername());

        // Mocking
        when(userService.findById(1L)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userInfoResponse);

        // Act
        UserInfoResponse result = userBusinessService.getUserInfo(user.getId());

        // Assert
        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        verify(userService, times(1)).findById(user.getId());
        verify(userMapper, times(1)).toDto(user);
    }
}