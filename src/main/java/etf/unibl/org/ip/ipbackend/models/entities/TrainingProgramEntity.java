package etf.unibl.org.ip.ipbackend.models.entities;

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

    private double price;

    private int level;

    private OffsetTime time;

    private String location;

    @OneToMany
    private List<ImageEntity> images;

    private String instructorInformation;

    private String contactNumber;
}
