package etf.unibl.org.ip.ipbackend.models.entities;

import etf.unibl.org.ip.ipbackend.models.enums.LocationType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "location")
@Data
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocationType type;

    private String location;
}
