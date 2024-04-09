package etf.unibl.org.ip.ipbackend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String location;

    @OneToOne
    private TrainingProgramEntity trainingProgram;

}
