package etf.unibl.org.ip.ipbackend.models.entities;

import etf.unibl.org.ip.ipbackend.models.enums.ProgramLevel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetTime;
import java.util.List;

@Entity
@Data
@Table(name = "program")
public class TrainingProgramEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @OneToOne
    private CategoryEntity category;

    @OneToOne
    private TraineeEntity creator;

    private double price;

    private ProgramLevel level;

    private OffsetTime time;

    private String location;

    private String image;

    private String instructorInformation;

    private String contactNumber;

    @OneToMany(fetch = FetchType.EAGER)
    private List<CommentEntity> comments;
}
