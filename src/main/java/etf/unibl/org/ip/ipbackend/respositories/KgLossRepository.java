package etf.unibl.org.ip.ipbackend.respositories;

import etf.unibl.org.ip.ipbackend.models.entities.KgLossEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KgLossRepository extends JpaRepository<KgLossEntity, Integer> {
    List<KgLossEntity> getAllByTraineeId(int trainee);
}
