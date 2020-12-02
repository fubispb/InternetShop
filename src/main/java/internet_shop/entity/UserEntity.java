package internet_shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity extends AbstractIdentifiableEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "id")
    private List<OrderEntity> orders;

}
