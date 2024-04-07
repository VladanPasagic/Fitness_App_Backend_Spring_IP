package etf.unibl.org.ip.ipbackend.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class AdviceEntity {
    @Id
    public Integer id;

    @OneToOne
    public UserEntity user;

    public String title;

    public String text;
}
