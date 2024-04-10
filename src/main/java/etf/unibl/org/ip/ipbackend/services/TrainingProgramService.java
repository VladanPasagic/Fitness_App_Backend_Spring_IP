package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.dtos.SingleTrainingProgram;
import etf.unibl.org.ip.ipbackend.models.dtos.TrainingProgram;
import etf.unibl.org.ip.ipbackend.models.requests.TrainingProgramRequest;

import java.util.List;

public interface TrainingProgramService {

    List<TrainingProgram> getAll();

    SingleTrainingProgram getById(int id);

    void save(TrainingProgramRequest trainingProgramRequest);
}
