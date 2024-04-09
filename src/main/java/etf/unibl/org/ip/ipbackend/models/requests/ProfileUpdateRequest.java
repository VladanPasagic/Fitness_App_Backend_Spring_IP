package etf.unibl.org.ip.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileUpdateRequest {
    @NotBlank
    private Integer id;

    @NotBlank
    private String mail;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String city;

    private MultipartFile avatar;
}
