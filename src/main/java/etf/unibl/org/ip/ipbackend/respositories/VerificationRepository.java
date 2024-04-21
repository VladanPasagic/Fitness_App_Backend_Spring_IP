package etf.unibl.org.ip.ipbackend.respositories;

import etf.unibl.org.ip.ipbackend.models.entities.VerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<VerificationEntity, Integer> {
    VerificationEntity getByToken(String token);
}
