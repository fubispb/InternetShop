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

    private ConnectBaseService connectBaseService;

    public ProductDAO(ConnectBaseService connectBaseService) {
        this.connectBaseService = connectBaseService;
    }

    public List<Product> getGeneralProductList() {
        List<Product> list = new ArrayList<>();
        try {
            connectBaseService.connect();
            ResultSet rs = connectBaseService.getStatement().executeQuery("" +
                    "SELECT product_id, name, price " +
                    "FROM products;");
            while (rs.next()) {
                Product product = new Product(
                        rs.getLong("product_id"),
                        rs.getString("name"),
                        rs.getInt("price"));
                list.add(product);
            }
        } catch (SQLException e) {
            log.error("Start log. " + e);
        } finally {
            connectBaseService.disconnect();
        }
        return list;
    }
}
