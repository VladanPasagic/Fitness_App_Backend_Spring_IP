package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.requests.EMailRequest;
import etf.unibl.org.ip.ipbackend.services.EMailService;
import etf.unibl.org.ip.ipbackend.services.LoggingService;
import etf.unibl.org.ip.ipbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

@Service
@RequiredArgsConstructor
public class EMailServiceImpl implements EMailService {

    private final JavaMailSender mailSender;
    private final Executor executorService;
    private final UserService userService;
    private final LoggingService loggingService;

    @Value("${email.username}")
    private String sender;

    @Override
    public void sendEmail(EMailRequest eMailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(getUserMail(eMailRequest.getId()));
        message.setSubject((eMailRequest.getSubject()));
        message.setText(eMailRequest.getText());
        executorService.execute(() -> {
            try {
                mailSender.send(message);
            } catch (Exception ex) {
                loggingService.log(LogLevel.ERROR, "Mail couldn't be sent");
            }
        });
    }

    private String getUserMail(Integer id) {
        var user = userService.getProfile(id);
        return user.getMail();
    }
}
