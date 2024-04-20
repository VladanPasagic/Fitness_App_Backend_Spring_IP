package etf.unibl.org.ip.ipbackend.respositories;

import etf.unibl.org.ip.ipbackend.models.entities.ParticipationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<ParticipationEntity, Integer> {
    List<ParticipationEntity> getParticipationEntitiesByTraineeId(int traineeId);
}
