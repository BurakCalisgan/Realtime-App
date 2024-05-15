package com.realtime.api.realtimeapp.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String CONTROLLER_BASE_PACKAGE = "com.realtime.api.realtimeapp";
    private static final String PARAMETER_DOCKET_TITLE = "Auth Operations";

    @Bean
    public GroupedOpenApi parameterApiGroup() {
        return GroupedOpenApi.builder()
                .group(PARAMETER_DOCKET_TITLE)
                .pathsToMatch("/api/auth/**",
                        "/api/user/**",
                        "/api/symbol/**",
                        "/api/currency-info/**")
                .packagesToScan(CONTROLLER_BASE_PACKAGE)
                .build();
    }

}
