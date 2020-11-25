package internet_shop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @Column(name = "product_id")
    private Long id;

    private String name;

    private int price;

    @ManyToOne
    private Country country;

}
