package etf.unibl.org.ip.ipbackend.models.dtos;

import lombok.Data;

@Data
public class Category {
    String name;

    List<SpecificAttribute> specificAttributes;
}
