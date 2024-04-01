package etf.unibl.org.ip.ipbackend.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "category")
@Data
public class CategoryEntity {
    @Id
    private Integer id;

    private String name;

    @OneToMany
    private List<SpecificAttributeEntity> specificAttributes;
}
