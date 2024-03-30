package etf.unibl.org.ip.ipbackend.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    private Integer id;

    private String username;

    private String password;

    private String lastName;

    private String firstName;

}
