package internet_shop.DAO;

import internet_shop.service.ConnectBaseService;
import internet_shop.model.Product;
import internet_shop.service.ProductService;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class BucketDAO {

    private ProductService productService;
    private ConnectBaseService connectBaseService;

    public BucketDAO(ProductService productService, ConnectBaseService connectBaseService) {
        this.productService = productService;
        this.connectBaseService = connectBaseService;
    }

    public Map<Product, Integer> getUserBucketById(long id) {
        Map<Product, Integer> bucket = new TreeMap<>();
        try {
            connectBaseService.connect();
            ResultSet rs = connectBaseService.getStatement().executeQuery("" +
                    "SELECT products_id, sum(count) " +
                    "FROM buckets " +
                    "WHERE users_id = '" + id + "' " +
                    "GROUP BY products_id;");
            while (rs.next()) {
                Product product = productService.getProductById(rs.getLong(1));
                int count = rs.getInt(2);
                bucket.put(product, count);
            }
        } catch (SQLException e) {
            log.error("Start log. " + e);
        } finally {
            connectBaseService.disconnect();
        }
        return bucket;
    }

    public void deleteFromUserBucket(long idUser, long idProduct) {
        try {
            connectBaseService.connect();
            connectBaseService.getStatement().executeUpdate("" +
                    "DELETE FROM buckets " +
                    "WHERE (products_id = '" + idProduct + "' " +
                    "AND users_id = '" + idUser + "');");
        } catch (SQLException e) {
            log.error("Start log. " + e);
        } finally {
            connectBaseService.disconnect();
        }
    }

    public void insertInUserBucket(Long id, Integer count) {
        try {
            connectBaseService.connect();
            PreparedStatement preparedStatement = connectBaseService.getConnection().prepareStatement("" +
                    "INSERT INTO buckets (users_id, products_id, count)" +
                    "VALUES (?, ?, ?);");
            preparedStatement.setLong(1, 1);
            preparedStatement.setLong(2, id);
            preparedStatement.setLong(3, count);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Start log. " + e);

        } finally {
            connectBaseService.disconnect();
        }
    }
}
