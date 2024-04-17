package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.dtos.Profile;
import etf.unibl.org.ip.ipbackend.models.entities.TraineeEntity;
import etf.unibl.org.ip.ipbackend.models.requests.ProfileUpdateRequest;
import etf.unibl.org.ip.ipbackend.models.requests.RegistrationRequest;
import etf.unibl.org.ip.ipbackend.respositories.TraineeRepository;
import etf.unibl.org.ip.ipbackend.services.LoggingService;
import etf.unibl.org.ip.ipbackend.services.StorageAccessService;
import etf.unibl.org.ip.ipbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final TraineeRepository traineeRepository;
    private final ModelMapper modelMapper;
    private final StorageAccessService storageAccessService;
    private final LoggingService loggingService;

    @Override
    public void register(RegistrationRequest request) throws ResponseStatusException, IOException {
        if (traineeRepository.existsByUsernameOrMail(request.getUsername(), request.getMail())) {
            loggingService.log(LogLevel.INFO, request.getUsername() + " tried to register with already registered email or username");
            throw new ResponseStatusException(HttpStatusCode.valueOf(409));
        }
        TraineeEntity trainee = createUserEntity(request);
        if (request.getAvatar() != null) {
            String savedFilePath = storageAccessService.saveToFile(request.getAvatar().getOriginalFilename(), request.getAvatar().getBytes());
            trainee.setAvatar(savedFilePath);
        }
        traineeRepository.saveAndFlush(trainee);
    }

    private TraineeEntity createUserEntity(RegistrationRequest request) {
        return modelMapper.map(request, TraineeEntity.class);
    }

    @Override
    public void updateProfile(Integer id, ProfileUpdateRequest request) throws IOException, ResponseStatusException {
        Optional<TraineeEntity> entity = traineeRepository.findById(id);
        if (entity.isPresent()) {
            TraineeEntity trainee = entity.get();
            trainee.setFirstName(request.getFirstName());
            trainee.setLastName(request.getLastName());
            trainee.setMail(request.getMail());
            trainee.setCity(request.getCity());
            if (request.getAvatar() != null) {
                String savedFilePath = storageAccessService.saveToFile(request.getAvatar().getOriginalFilename(), request.getAvatar().getBytes());
                trainee.setAvatar(savedFilePath);
            }
            traineeRepository.saveAndFlush(trainee);
        } else {
            loggingService.log(LogLevel.INFO, "Trying to access non existent profile with the following id " + id);
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    @Override
    public Profile getProfile(Integer id) {
        Optional<TraineeEntity> trainee = traineeRepository.findById(id);
        if (trainee.isEmpty()) {
            loggingService.log(LogLevel.INFO, "Trying to access non existent profile with the following id " + id);
            throw new ResponseStatusException(HttpStatusCode.valueOf(204));
        }
        return getProfileFromTraineeEntity(trainee.get());
    }

    private Profile getProfileFromTraineeEntity(TraineeEntity traineeEntity) {
        return modelMapper.map(traineeEntity, Profile.class);
    }

}
