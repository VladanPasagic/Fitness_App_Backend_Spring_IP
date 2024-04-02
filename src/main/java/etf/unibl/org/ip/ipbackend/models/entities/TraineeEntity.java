package etf.unibl.org.ip.ipbackend.models.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class TraineeEntity extends UserEntity {
    private String avatar;

    private String mail;

    private String city;
}
