package com.smartCode.audit_traill.listener;

import com.smartCode.audit_traill.model.dto.CreateActionDto;
import com.smartCode.audit_traill.service.action.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActionKafkaListener {
    private final ActionService actionService;

    @KafkaListener(
            topics = "notify",
            containerFactory = "kafkaListenerContainerFactory",
            autoStartup = "${listen.auto.start:false}")
    public void CreateListener(CreateActionDto notificationDto) {
        actionService.create(notificationDto);

    }
}