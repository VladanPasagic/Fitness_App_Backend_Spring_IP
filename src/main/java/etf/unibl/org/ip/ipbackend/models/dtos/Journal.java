package etf.unibl.org.ip.ipbackend.models.dtos;

import etf.unibl.org.ip.ipbackend.models.enums.ProgramLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Journal {
    private int id;
    private String type;
    private int duration;
    private ProgramLevel intensity;
    private int result;
}
