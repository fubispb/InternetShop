package internet_shop.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "countries")
public class CountryEntity extends AbstractIdentifiableEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "id")
    private List<ProductEntity> products;


}
