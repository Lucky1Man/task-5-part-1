package org.example.task5part1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.profitsoft.kafka.messages.SimpleEmailDto;
import org.example.task5part1.document.SendMailRequest;
import org.example.task5part1.document.SendStatus;
import org.example.task5part1.repository.SendMailRequestRepository;
import org.example.task5part1.service.MailSenderService;
import org.example.task5part1.service.MailService;
import org.example.task5part1.service.TimeService;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    private final MailSenderService mailSenderService;

    private final SendMailRequestRepository mailRequestRepository;

    private final TimeService timeService;

    @Override
    public void send(SimpleEmailDto emailDto) {
        SendMailRequest saved = mailRequestRepository.save(mapToRequest(emailDto));
        try {
            mailSenderService.send(emailDto);
            saved.setSendStatus(SendStatus.SENT);
        } catch (MailException ex) {
            saved.setSendStatus(SendStatus.ERROR);
            saved.setErrorMessage(ex.getMessage());
        } finally {
            mailRequestRepository.save(saved);
        }
    }

    private SendMailRequest mapToRequest(SimpleEmailDto emailDto) {
        return SendMailRequest.builder()
                .withFrom(emailDto.getFrom())
                .withTo(emailDto.getTo())
                .withSubject(emailDto.getSubject())
                .withContent(emailDto.getText())
                .withSendStatus(SendStatus.PENDING)
                .withNumSendAttempts(1)
                .withLastSendAttempt(timeService.instantUtcNow())
                .withSentDate(emailDto.getSentDate())
                .build();
    }
}
