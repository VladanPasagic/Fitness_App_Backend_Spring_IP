package etf.unibl.org.ip.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegistrationRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String city;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private MultipartFile avatar;

    private String mail;
}
