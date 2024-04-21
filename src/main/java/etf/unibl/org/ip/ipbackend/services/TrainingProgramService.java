package etf.unibl.org.ip.ipbackend.services;

import etf.unibl.org.ip.ipbackend.models.dtos.Participation;
import etf.unibl.org.ip.ipbackend.models.dtos.SingleTrainingProgram;
import etf.unibl.org.ip.ipbackend.models.dtos.TrainingProgram;
import etf.unibl.org.ip.ipbackend.models.requests.TrainingProgramRequest;
import jakarta.mail.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface TrainingProgramService {

    Page<TrainingProgram> getAll(Pageable page);

    Page<TrainingProgram> getAllFromTrainee(Pageable page, int id);

    SingleTrainingProgram getById(int id);

    void save(TrainingProgramRequest trainingProgramRequest) throws IOException;

    void participate(int userId, int trainingProgramId);

    List<Participation> getAllParticipation(int traineeId);
}
