package etf.unibl.org.ip.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EMailRequest {
    @NotBlank
    private Integer id;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;
}
