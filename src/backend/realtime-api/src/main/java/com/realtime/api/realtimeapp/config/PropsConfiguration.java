package com.realtime.api.realtimeapp.config;

import com.realtime.api.realtimeapp.util.props.JwtProperties;
import com.realtime.api.realtimeapp.util.props.RealtimeAppProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({RealtimeAppProperties.class, JwtProperties.class})
public class PropsConfiguration {
}
