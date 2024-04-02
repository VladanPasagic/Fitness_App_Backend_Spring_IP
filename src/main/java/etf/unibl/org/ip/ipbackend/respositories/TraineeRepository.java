package etf.unibl.org.ip.ipbackend.respositories;

import etf.unibl.org.ip.ipbackend.models.entities.TraineeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TraineeRepository extends JpaRepository<TraineeEntity, Integer> {
    boolean existsByUsernameOrMail(String username, String mail);

    Optional<TraineeEntity> findByUsernameAndPassword(String username, String password);
}
