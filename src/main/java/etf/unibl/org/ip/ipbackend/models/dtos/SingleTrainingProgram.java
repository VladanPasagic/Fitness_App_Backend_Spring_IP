package etf.unibl.org.ip.ipbackend.models.dtos;

import etf.unibl.org.ip.ipbackend.models.entities.ImageEntity;
import lombok.Data;

import java.time.OffsetTime;
import java.util.List;

@Data
public class SingleTrainingProgram {
    private Integer id;
    private String name;
    private String description;
    private Category category;
    private double price;
    private int level;
    private OffsetTime time;
    private String location;
    private List<ImageEntity> images;
    private String instructorInformation;
    private String contactNumber;
}
