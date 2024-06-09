package org.example.task5part1.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.profitsoft.kafka.messages.SimpleEmailDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailListener {

    @KafkaListener(topics = "${kafka.topic.simpleEmail}")
    public void paymentReceived(SimpleEmailDto emailDto) {
        log.info("{}", emailDto);
    }


}
