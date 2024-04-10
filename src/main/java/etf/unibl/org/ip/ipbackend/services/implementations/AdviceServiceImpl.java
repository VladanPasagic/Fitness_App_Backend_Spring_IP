package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.entities.AdviceEntity;
import etf.unibl.org.ip.ipbackend.models.entities.UserEntity;
import etf.unibl.org.ip.ipbackend.models.requests.AdviceRequest;
import etf.unibl.org.ip.ipbackend.respositories.AdviceRepository;
import etf.unibl.org.ip.ipbackend.respositories.UserRepository;
import etf.unibl.org.ip.ipbackend.services.AdviceService;
import etf.unibl.org.ip.ipbackend.services.LoggingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdviceServiceImpl implements AdviceService {
    private final AdviceRepository adviceRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final LoggingService loggingService;

    @Override
    public void save(AdviceRequest advice) {
        adviceRepository.save(createAdviceEntity(advice));
    }

    private AdviceEntity createAdviceEntity(AdviceRequest request) {
        AdviceEntity adviceEntity = modelMapper.map(request, AdviceEntity.class);
        adviceEntity.setId(null);
        Optional<UserEntity> userEntity = userRepository.findById(request.getUserId());
        if (userEntity.isPresent()) {
            adviceEntity.setUser(userEntity.get());
        } else {
            loggingService.log(LogLevel.INFO, "Couldn't find user with id=" + request.getUserId());
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        return modelMapper.map(request, AdviceEntity.class);
    }
}
