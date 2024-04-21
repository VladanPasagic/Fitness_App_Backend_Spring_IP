package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.entities.TraineeEntity;
import etf.unibl.org.ip.ipbackend.models.entities.UserEntity;
import etf.unibl.org.ip.ipbackend.models.entities.VerificationEntity;
import etf.unibl.org.ip.ipbackend.models.requests.EMailRequest;
import etf.unibl.org.ip.ipbackend.respositories.TraineeRepository;
import etf.unibl.org.ip.ipbackend.respositories.VerificationRepository;
import etf.unibl.org.ip.ipbackend.services.EMailService;
import etf.unibl.org.ip.ipbackend.services.LoggingService;
import etf.unibl.org.ip.ipbackend.services.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {
    @Value("${frontend-url}")
    private String frontendUrl;


    private final VerificationRepository verificationRepository;
    private final LoggingService loggingService;
    private final TraineeRepository traineeRepository;
    private final EMailService eMailService;

    @Override
    public void verify(String token) {
        VerificationEntity verificationEntity = verificationRepository.getByToken(token);
        if (verificationEntity != null) {
            TraineeEntity trainee = verificationEntity.getUser();
            trainee.setVerified(true);
            traineeRepository.save(trainee);
        } else {
            loggingService.log(LogLevel.INFO, "Invalid token entered " + token);
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    @Override
    public void createToken(TraineeEntity trainee) {
        String token = UUID.randomUUID().toString();
        VerificationEntity verificationEntity = new VerificationEntity();
        verificationEntity.setToken(token);
        verificationEntity.setUser(trainee);
        verificationRepository.save(verificationEntity);
        loggingService.log(LogLevel.INFO, "Created token " + token);
        EMailRequest request = new EMailRequest();
        request.setSubject("Verify your email");
        request.setText("Follow the next link to verify your email: " + frontendUrl + "/verify/" + token);
        request.setId(trainee.getId());
        eMailService.sendEmail(request);
    }
}
