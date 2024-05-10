package com.realtime.api.realtimeapp.util.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@Data
@ConfigurationProperties(prefix = "spring.mail")
public class SmtpProperties {
    private String host;
    private int port;
    private String protocol;
    private String username;
    private String password;
    private Properties properties;
}
