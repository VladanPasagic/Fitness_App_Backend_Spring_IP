package etf.unibl.org.ip.ipbackend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class TraineeEntity extends UserEntity {

    private String avatar;

    @Column(unique = true)
    private String mail;

    private String city;

    private boolean isVerified;
}
