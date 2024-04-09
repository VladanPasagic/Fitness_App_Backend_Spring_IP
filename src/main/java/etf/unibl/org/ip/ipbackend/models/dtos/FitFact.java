package etf.unibl.org.ip.ipbackend.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FitFact {
    private String category;
    private String title;
    private String description;
    private String link;
}
