package com.realtime.api.realtimeapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Slf4j
@SpringBootApplication
public class RealtimeAppApplication {

    public static void main(String[] args) {

        String activeProfile = System.getProperty("spring.profiles.active");
        log.info("active profile : {}",activeProfile);
        if (activeProfile == null || activeProfile.isEmpty()) {
            activeProfile = "dev";
        }
        loadEnvironmentFile(activeProfile);

        SpringApplication.run(RealtimeAppApplication.class, args);
    }

    private static void loadEnvironmentFile(String activeProfile) {
        ClassPathResource resource = new ClassPathResource("environments/" + activeProfile + ".env");
        Properties properties = new Properties();
        try (InputStream inputStream = resource.getInputStream()) {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("Exception: {}", e.toString());
        }

        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            log.info("value : {}", value);
            System.setProperty(key, value);
        }
    }

}
