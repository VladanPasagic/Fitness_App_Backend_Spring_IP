package etf.unibl.org.ip.ipbackend.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalRequest {

    @NotBlank
    private String type;

    @NotNull
    private int duration;

    @NotNull
    private int intensity;

    @NotNull
    private int result;
}
