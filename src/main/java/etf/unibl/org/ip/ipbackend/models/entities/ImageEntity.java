package etf.unibl.org.ip.ipbackend.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "image")
public class ImageEntity {
    @Id
    private Integer id;

    private String location;

    @OneToOne
    private TrainingProgramEntity trainingProgram;

}
