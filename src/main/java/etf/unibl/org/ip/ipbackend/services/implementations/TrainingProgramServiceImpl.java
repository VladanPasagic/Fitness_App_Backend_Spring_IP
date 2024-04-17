package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.models.dtos.SingleTrainingProgram;
import etf.unibl.org.ip.ipbackend.models.dtos.TrainingProgram;
import etf.unibl.org.ip.ipbackend.models.entities.TrainingProgramEntity;
import etf.unibl.org.ip.ipbackend.models.requests.TrainingProgramRequest;
import etf.unibl.org.ip.ipbackend.respositories.TrainingProgramRepository;
import etf.unibl.org.ip.ipbackend.services.TrainingProgramService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingProgramServiceImpl implements TrainingProgramService {
    private final TrainingProgramRepository trainingProgramRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TrainingProgram> getAll() {
        return trainingProgramRepository.findAll().stream().map(tp -> modelMapper.map(tp, TrainingProgram.class)).toList();
    }

    @Override
    public SingleTrainingProgram getById(int id) {
        Optional<TrainingProgramEntity> entity = trainingProgramRepository.findById(id);
        return entity.map(trainingProgramEntity -> modelMapper.map(trainingProgramEntity, SingleTrainingProgram.class)).orElse(null);
    }

    @Override
    public void save(TrainingProgramRequest trainingProgram) {
        TrainingProgramEntity entity = modelMapper.map(trainingProgram, TrainingProgramEntity.class);
        trainingProgramRepository.save(entity);
        //save images
    }
}
