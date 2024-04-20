package etf.unibl.org.ip.ipbackend.respositories;

import etf.unibl.org.ip.ipbackend.models.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    List<CommentEntity> getCommentEntitiesByTrainingProgramId(int trainingProgramId);
}
