package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.dtos.Participation;
import etf.unibl.org.ip.ipbackend.models.dtos.SingleTrainingProgram;
import etf.unibl.org.ip.ipbackend.models.dtos.TrainingProgram;
import etf.unibl.org.ip.ipbackend.models.entities.*;
import etf.unibl.org.ip.ipbackend.models.enums.LocationType;
import etf.unibl.org.ip.ipbackend.models.enums.ProgramLevel;
import etf.unibl.org.ip.ipbackend.models.requests.TrainingProgramRequest;
import etf.unibl.org.ip.ipbackend.respositories.*;
import etf.unibl.org.ip.ipbackend.services.LoggingService;
import etf.unibl.org.ip.ipbackend.services.StorageAccessService;
import etf.unibl.org.ip.ipbackend.services.TrainingProgramService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.logging.LogLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingProgramServiceImpl implements TrainingProgramService {
    private final TrainingProgramRepository trainingProgramRepository;
    private final CategoryRepository categoryRepository;
    private final StorageAccessService storageAccessService;
    private final LoggingService loggingService;
    private final LocationRepository locationRepository;
    private final TraineeRepository traineeRepository;
    private final ParticipationRepository participationRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<TrainingProgram> getAll(Pageable page) {
        return trainingProgramRepository.findAllByActiveIsTrue(page).map(tp -> modelMapper.map(tp, TrainingProgram.class));
    }

    @Override
    public Page<TrainingProgram> getAllFromTrainee(Pageable page, int id) {
        return trainingProgramRepository.findAllByCreatorIdAndActiveIsTrue(id, page).map(tp -> modelMapper.map(tp, TrainingProgram.class));
    }


    @Override
    public SingleTrainingProgram getById(int id) {
        Optional<TrainingProgramEntity> entity = trainingProgramRepository.findById(id);
        if (entity.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        return entity.map(trainingProgramEntity -> modelMapper.map(trainingProgramEntity, SingleTrainingProgram.class)).orElse(null);
    }

    @Override
    public void save(TrainingProgramRequest trainingProgram) throws IOException {
        TrainingProgramEntity entity = modelMapper.map(trainingProgram, TrainingProgramEntity.class);
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(Integer.parseInt(trainingProgram.getCategoryId()));
        entity.setPrice(Double.parseDouble(trainingProgram.getPrice()));
        entity.setActive(true);
        if (categoryEntity.isPresent()) {
            entity.setCategory(categoryEntity.get());
        } else {
            loggingService.log(LogLevel.INFO, "Category not found");
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setLocation(trainingProgram.getLocationName());

        switch (trainingProgram.getLocationId()) {
            case "0":
                locationEntity.setType(LocationType.Offline);
                break;
            case "1":
                locationEntity.setType(LocationType.Online);
                break;
        }
        locationRepository.save(locationEntity);
        entity.setLocation(locationEntity);
        switch (trainingProgram.getLevel()) {
            case "0":
                entity.setLevel(ProgramLevel.Beginner);
                break;
            case "1":
                entity.setLevel(ProgramLevel.Intermediate);
                break;
            case "2":
                entity.setLevel(ProgramLevel.Hard);
                break;
        }
        String savedFilePath = storageAccessService.saveToFile(trainingProgram.getImage().getOriginalFilename(), trainingProgram.getImage().getBytes());
        entity.setImage(savedFilePath);
        trainingProgramRepository.save(entity);
    }

    @Override
    public void participate(int userId, int trainingProgramId) {
        ParticipationEntity entity = new ParticipationEntity();
        Optional<TrainingProgramEntity> trainingProgramEntity = trainingProgramRepository.findById(trainingProgramId);
        Optional<TraineeEntity> traineeEntity = traineeRepository.findById(userId);
        if (trainingProgramEntity.isPresent() && traineeEntity.isPresent()) {
            entity.setTrainingProgram(trainingProgramEntity.get());
            entity.setTrainee(traineeEntity.get());
            entity.setDate(new Date(System.currentTimeMillis()));
            participationRepository.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }


    }

    @Override
    public List<Participation> getAllParticipation(int traineeId) {
        return participationRepository.getParticipationEntitiesByTraineeId(traineeId).stream().map(p -> modelMapper.map(p, Participation.class)).toList();
    }
}
