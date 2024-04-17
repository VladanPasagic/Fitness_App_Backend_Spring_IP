package etf.unibl.org.ip.ipbackend.models.requests;

import etf.unibl.org.ip.ipbackend.models.dtos.Category;
import etf.unibl.org.ip.ipbackend.models.enums.ProgramLevel;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TrainingProgramRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private Category category;

    @NotBlank
    private double price;

    @NotBlank
    private ProgramLevel level;

    @NotBlank
    private String location;

    private String instructorInformation;

    private String contactNumber;
}
