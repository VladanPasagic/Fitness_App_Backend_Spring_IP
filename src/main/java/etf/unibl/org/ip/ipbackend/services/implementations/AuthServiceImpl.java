package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.entities.TraineeEntity;
import etf.unibl.org.ip.ipbackend.models.requests.LoginRequest;
import etf.unibl.org.ip.ipbackend.models.responses.LoginResponse;
import etf.unibl.org.ip.ipbackend.respositories.TraineeRepository;
import etf.unibl.org.ip.ipbackend.services.AuthService;
import etf.unibl.org.ip.ipbackend.services.LoggingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final TraineeRepository traineeRepository;
    private final LoggingService loggingService;

    @Override
    public LoginResponse login(LoginRequest request) throws ResponseStatusException {
        Optional<TraineeEntity> traineeEntity = traineeRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (traineeEntity.isEmpty()) {
            loggingService.log(LogLevel.INFO, "Invalid credentials");
            throw new ResponseStatusException(HttpStatusCode.valueOf(401));
        }
        return new LoginResponse(traineeEntity.get().getId(), "samo nesto");
    }
}
