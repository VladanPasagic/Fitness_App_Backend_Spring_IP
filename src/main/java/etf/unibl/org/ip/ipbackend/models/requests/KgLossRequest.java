package etf.unibl.org.ip.ipbackend.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KgLossRequest {
    @NotNull
    private double kg;
}
