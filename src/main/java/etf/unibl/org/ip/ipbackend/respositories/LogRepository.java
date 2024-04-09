package etf.unibl.org.ip.ipbackend.respositories;

import etf.unibl.org.ip.ipbackend.models.entities.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, Integer> {
}
