package etf.unibl.org.ip.ipbackend.models.dtos;

import etf.unibl.org.ip.ipbackend.models.enums.ProgramLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Journal {
    private int id;
    private String type;
    private int duration;
    private ProgramLevel intensity;
    private int result;
    private Date date;
}
