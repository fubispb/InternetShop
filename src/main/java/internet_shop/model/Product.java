package internet_shop.model;

import lombok.Data;

@Data
public class Product implements Comparable<Product> {

    private Long id;
    private String name;
    private int price;


    public Product(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(Product o) {
        return this.id.compareTo(o.id);
    }
}
