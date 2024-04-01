package etf.unibl.org.ip.ipbackend.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "specific_attribute")
public class SpecificAttributeEntity {
    @Id
    private Integer id;

    private String attribute;
}
