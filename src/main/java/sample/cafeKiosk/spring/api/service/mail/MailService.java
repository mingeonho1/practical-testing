package sample.cafeKiosk.spring.api.service.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafeKiosk.spring.client.mail.MailSendClient;
import sample.cafeKiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafeKiosk.spring.domain.history.mail.MailSendHistoryRepository;

@RequiredArgsConstructor
@Service
public class MailService {

    private final MailSendClient mailSendClient;
    private final MailSendHistoryRepository mailSendHistoryRepository;

    public boolean sendMail(String fromEmail, String toEmail, String subject, String content) {

        boolean result = mailSendClient.sendMail(fromEmail, toEmail, subject, content);

        MailSendHistory mailSendHistory = MailSendHistory.builder()
            .fromEmail(fromEmail)
            .toEmail(toEmail)
            .subject(subject)
            .content(content)
            .build();

        if (result) {
            mailSendHistoryRepository.save(mailSendHistory);
            return true;
        }

        return false;
    }
}

