package etf.unibl.org.ip.ipbackend.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.logging.LogLevel;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "log")
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LogLevel level;

    private String message;

    private Date timestamp;

    public LogEntity(LogLevel level, String message, Date date) {
        this.level = level;
        this.message = message;
        this.timestamp = date;
    }
}
