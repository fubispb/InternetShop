package internet_shop.DAO;

import internet_shop.model.Product;
import internet_shop.service.ConnectBaseService;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProductDAO {

    public List<Product> getGeneralProductList() {
        List<Product> list = new ArrayList<>();
        try {
            ConnectBaseService.connect();
            ResultSet rs = ConnectBaseService.statement.executeQuery("" +
                    "SELECT product_id, name, price " +
                    "FROM products;");
            while (rs.next()) {
                Product product = new Product(
                        rs.getLong("product_id"),
                        rs.getString("name"),
                        rs.getInt("price"));
                list.add(product);
            }
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Start log. " + e);
        } finally {
            ConnectBaseService.disconnect();
        }
        return list;
    }
}
