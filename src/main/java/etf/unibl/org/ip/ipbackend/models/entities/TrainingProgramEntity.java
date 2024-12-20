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

    @ManyToOne
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.EAGER)
    private TraineeEntity creator;

    private double price;

    private ProgramLevel level;

    private OffsetTime time;

    @ManyToOne(fetch = FetchType.EAGER)
    private LocationEntity location;

    private boolean active;

    private String image;

    private String instructorInformation;

    private String contactNumber;
}
