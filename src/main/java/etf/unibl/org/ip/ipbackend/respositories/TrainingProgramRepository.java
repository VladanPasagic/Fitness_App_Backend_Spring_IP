package etf.unibl.org.ip.ipbackend.respositories;

import etf.unibl.org.ip.ipbackend.models.entities.TrainingProgramEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingProgramRepository extends JpaRepository<TrainingProgramEntity, Integer> {
    Page<TrainingProgramEntity> findAllByCreatorIdAndActiveIsTrue(int id, Pageable pageable);

    Page<TrainingProgramEntity> findAllByActiveIsTrue(Pageable pageable);
}
