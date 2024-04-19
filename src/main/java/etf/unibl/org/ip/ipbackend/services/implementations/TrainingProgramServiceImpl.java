package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.dtos.SingleTrainingProgram;
import etf.unibl.org.ip.ipbackend.models.dtos.TrainingProgram;
import etf.unibl.org.ip.ipbackend.models.entities.CategoryEntity;
import etf.unibl.org.ip.ipbackend.models.entities.TrainingProgramEntity;
import etf.unibl.org.ip.ipbackend.models.enums.ProgramLevel;
import etf.unibl.org.ip.ipbackend.models.requests.TrainingProgramRequest;
import etf.unibl.org.ip.ipbackend.respositories.CategoryRepository;
import etf.unibl.org.ip.ipbackend.respositories.TrainingProgramRepository;
import etf.unibl.org.ip.ipbackend.services.LoggingService;
import etf.unibl.org.ip.ipbackend.services.StorageAccessService;
import etf.unibl.org.ip.ipbackend.services.TrainingProgramService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingProgramServiceImpl implements TrainingProgramService {
    private final TrainingProgramRepository trainingProgramRepository;
    private final CategoryRepository categoryRepository;
    private final StorageAccessService storageAccessService;
    private final LoggingService loggingService;
    private final ModelMapper modelMapper;

    @Override
    public List<TrainingProgram> getAll() {
        return trainingProgramRepository.findAll().stream().map(tp -> modelMapper.map(tp, TrainingProgram.class)).toList();
    }

    @Override
    public List<TrainingProgram> getAllFromTrainee(int id) {
        return trainingProgramRepository.findAllByCreatorId(id).stream().map(tp -> modelMapper.map(tp, TrainingProgram.class)).toList();
    }


    @Override
    public SingleTrainingProgram getById(int id) {
        Optional<TrainingProgramEntity> entity = trainingProgramRepository.findById(id);
        return entity.map(trainingProgramEntity -> modelMapper.map(trainingProgramEntity, SingleTrainingProgram.class)).orElse(null);
    }

    @Override
    public void save(TrainingProgramRequest trainingProgram) throws IOException {
        TrainingProgramEntity entity = modelMapper.map(trainingProgram, TrainingProgramEntity.class);
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(Integer.parseInt(trainingProgram.getCategoryId()));
        entity.setPrice(Double.parseDouble(trainingProgram.getPrice()));
        if (categoryEntity.isPresent()) {
            entity.setCategory(categoryEntity.get());
        } else {
            loggingService.log(LogLevel.INFO, "Category not found");
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
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
}
