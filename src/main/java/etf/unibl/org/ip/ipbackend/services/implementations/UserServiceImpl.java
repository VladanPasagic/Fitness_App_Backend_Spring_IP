package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.dtos.Profile;
import etf.unibl.org.ip.ipbackend.models.entities.TraineeEntity;
import etf.unibl.org.ip.ipbackend.models.requests.ProfileUpdateRequest;
import etf.unibl.org.ip.ipbackend.models.requests.RegistrationRequest;
import etf.unibl.org.ip.ipbackend.respositories.TraineeRepository;
import etf.unibl.org.ip.ipbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final TraineeRepository traineeRepository;
    private final ModelMapper modelMapper;

    @Override
    public void register(RegistrationRequest request) throws ResponseStatusException {
        if (traineeRepository.existsByUsernameOrMail(request.getUsername(), request.getMail())) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(409));
        }
        //save multipart file
        traineeRepository.saveAndFlush(createUserEntity(request));
    }

    private TraineeEntity createUserEntity(RegistrationRequest request) {
        return modelMapper.map(request, TraineeEntity.class);
    }

    @Override
    public void updateProfile(ProfileUpdateRequest request) {

    }

    @Override
    public Profile getProfile(Integer id) {
        Optional<TraineeEntity> trainee = traineeRepository.findById(id);
        if (trainee.isEmpty())
            throw new ResponseStatusException(HttpStatusCode.valueOf(204));
        return getProfileFromTraineeEntity(trainee.get());
    }

    private Profile getProfileFromTraineeEntity(TraineeEntity traineeEntity) {
        return modelMapper.map(traineeEntity, Profile.class);
    }

}
