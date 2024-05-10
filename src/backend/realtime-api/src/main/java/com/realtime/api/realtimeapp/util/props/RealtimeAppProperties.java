package com.realtime.api.realtimeapp.util.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "realtime-app")
public class RealtimeAppProperties {
}
