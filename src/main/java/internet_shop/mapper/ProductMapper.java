package internet_shop.mapper;

import internet_shop.model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class ProductMapper implements RowMapper<Product> {

    public static final String BASE_SQL = "SELECT product_id, name, price FROM products;";

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong(1);
        String name = rs.getString(2);
        int price = rs.getInt(3);
        return new Product(id, name, price);
    }
}
