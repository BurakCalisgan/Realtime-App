package com.realtime.api.realtimeapp.service.consumer;

import com.realtime.api.realtimeapp.model.dto.request.SendMailRequestDto;
import com.realtime.api.realtimeapp.service.external.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailConsumerService {

    private final MailService mailService;

    @KafkaListener(
            topics = "${spring.kafka.template.default-topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void listen(@Payload SendMailRequestDto message) {

        log.info("Message received.. To : {} Subject: {} Text : {}",
                message.getTo(),
                message.getSubject(),
                message.getText());

        mailService.send(message.getTo(), message.getSubject(), message.getText());

    }
}
