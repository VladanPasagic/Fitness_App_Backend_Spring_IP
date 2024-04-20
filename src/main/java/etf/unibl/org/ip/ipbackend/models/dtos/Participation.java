package etf.unibl.org.ip.ipbackend.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participation {
    private int id;
    private Trainee trainee;
    private TrainingProgram trainingProgram;
    private Date date;
}
