package internet_shop.model;


import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1590813466145354671L;
    private final long id;
    private final String name;
    private Map<Product, Integer> bucket;


    public User(long id, String name, Map<Product, Integer> bucket) {
        this.id = id;
        this.name = name;
        this.bucket = bucket;
    }

    public void removeFromBucketByProduct(Product product) {
        bucket.remove(product);
    }
}
