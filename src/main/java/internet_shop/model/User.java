package internet_shop.model;


import lombok.Data;

import java.util.Map;

@Data
public class User {

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
