package etf.unibl.org.ip.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdviceRequest {
    @NotBlank
    private Integer userId;

    @NotBlank
    private String title;

    @NotBlank
    private String text;
}
