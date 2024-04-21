package etf.unibl.org.ip.ipbackend.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "verify")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    private TraineeEntity user;

    private String token;

}
