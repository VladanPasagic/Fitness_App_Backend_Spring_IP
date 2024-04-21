package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.dtos.Profile;
import etf.unibl.org.ip.ipbackend.models.entities.TraineeEntity;
import etf.unibl.org.ip.ipbackend.respositories.TraineeRepository;
import etf.unibl.org.ip.ipbackend.services.LoggingService;
import etf.unibl.org.ip.ipbackend.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final TraineeRepository traineeRepository;
    private final LoggingService loggingService;
    private final ModelMapper modelMapper;

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
