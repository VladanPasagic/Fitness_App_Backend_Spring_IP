package etf.unibl.org.ip.ipbackend.models.dtos;

import lombok.Data;

@Data
public class TrainingProgram {
    private Integer id;
    private String name;
    private double price;
    private String image;
}
