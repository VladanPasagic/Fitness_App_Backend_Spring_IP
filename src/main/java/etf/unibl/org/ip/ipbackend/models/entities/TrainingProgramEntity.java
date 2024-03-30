package etf.unibl.org.ip.ipbackend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "program")
public class TrainingProgramEntity {
    @Id
    private Integer id;

    private String name;

    private String description;

    @OneToOne
    private CategoryEntity category;
}
