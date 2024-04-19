package etf.unibl.org.ip.ipbackend.models.dtos;

import etf.unibl.org.ip.ipbackend.models.entities.TraineeEntity;
import etf.unibl.org.ip.ipbackend.models.enums.ProgramLevel;
import lombok.Data;

import java.time.OffsetTime;

@Data
public class SingleTrainingProgram {
    private Integer id;
    private String name;
    private String description;
    private Category category;
    private double price;
    private ProgramLevel level;
    private TraineeEntity trainee;
    private OffsetTime time;
    private String location;
    private String image;
    private String instructorInformation;
    private String contactNumber;
}
