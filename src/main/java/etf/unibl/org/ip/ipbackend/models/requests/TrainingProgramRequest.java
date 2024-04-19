package etf.unibl.org.ip.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TrainingProgramRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String categoryId;

    @NotBlank
    private String price;

    @NotBlank
    private String level;

    @NotBlank
    private String location;

    @NotBlank
    private String creatorId;

    private MultipartFile image;

    private String instructorInformation;

    private String contactNumber;
}
