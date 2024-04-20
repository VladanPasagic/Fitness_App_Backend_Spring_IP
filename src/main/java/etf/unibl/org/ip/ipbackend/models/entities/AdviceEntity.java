package etf.unibl.org.ip.ipbackend.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "advice")
public class AdviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String text;

    @ManyToOne
    private UserEntity user;

    private boolean isRead;
}
