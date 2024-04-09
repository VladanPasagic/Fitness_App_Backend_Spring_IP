package etf.unibl.org.ip.ipbackend.models.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Profile {
    private String mail;
    private String firstName;
    private String lastName;
    private String city;
    private MultipartFile avatar;
}
