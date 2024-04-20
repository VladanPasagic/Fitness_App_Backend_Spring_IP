package etf.unibl.org.ip.ipbackend.models.dtos;

import etf.unibl.org.ip.ipbackend.models.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private int id;
    private LocationType type;
    private String location;
}
