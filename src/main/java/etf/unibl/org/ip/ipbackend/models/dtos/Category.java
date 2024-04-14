package etf.unibl.org.ip.ipbackend.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class Category {
    int id;
    String name;
    List<SpecificAttribute> specificAttributes;
}
