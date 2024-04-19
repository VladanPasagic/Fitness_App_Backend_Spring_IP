package etf.unibl.org.ip.ipbackend.respositories;

import etf.unibl.org.ip.ipbackend.models.entities.TrainingProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingProgramRepository extends JpaRepository<TrainingProgramEntity, Integer> {
    List<TrainingProgramEntity> findAllByCreatorIdAndActiveIsTrue(int id);

    List<TrainingProgramEntity> findAllByActiveIsTrue();
}
