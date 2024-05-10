package com.realtime.api.realtimeapp.service.external;

public interface MailService {

    void send(String to, String subject, String text);
}
