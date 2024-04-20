package etf.unibl.org.ip.ipbackend.models.entities;

import etf.unibl.org.ip.ipbackend.models.enums.ProgramLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "journal")
public class JournalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private TraineeEntity trainee;

    private String type;

    private int duration;

    private ProgramLevel intensity;

    private int result;
}
