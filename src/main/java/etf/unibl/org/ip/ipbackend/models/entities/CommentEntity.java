package etf.unibl.org.ip.ipbackend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private UserEntity user;

    private String comment;

    private Date date;

    @ManyToOne
    private TrainingProgramEntity trainingProgram;
}
