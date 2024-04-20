package etf.unibl.org.ip.ipbackend.services.implementations;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import etf.unibl.org.ip.ipbackend.models.requests.EMailRequest;
import etf.unibl.org.ip.ipbackend.services.EMailService;
import etf.unibl.org.ip.ipbackend.services.LoggingService;
import etf.unibl.org.ip.ipbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EMailServiceImpl implements EMailService {

    private final UserService userService;
    private final LoggingService loggingService;

    @Value("${mail.api.key}")
    private String apiKey;

    @Value("${email.username}")
    private String sender;

    @Override
    public void sendEmail(EMailRequest eMailRequest) {
        String subject = eMailRequest.getSubject();
        Email from = new Email(sender);
        Email to = new Email(getUserMail(eMailRequest.getId()));
        Content content = new Content("text/plain", eMailRequest.getText());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            loggingService.log(LogLevel.ERROR, "Error occurred when sending email");
            throw new RuntimeException(e);
        }
    }

    private String getUserMail(Integer id) {
        var user = userService.getProfile(id);
        return user.getMail();
    }
}
