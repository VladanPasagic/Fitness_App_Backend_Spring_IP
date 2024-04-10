package etf.unibl.org.ip.ipbackend.models.dtos;

import etf.unibl.org.ip.ipbackend.models.entities.ImageEntity;
import lombok.Data;

import java.util.List;

@Data
public class TrainingProgram {
    private Integer id;
    private String name;
    private double price;
    private List<ImageEntity> images;
}
