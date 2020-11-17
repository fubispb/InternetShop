package internet_shop.DAO;

import internet_shop.model.User;
import internet_shop.service.BucketService;
import internet_shop.service.ConnectBaseService;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class UserDAO {

    private BucketService bucketService;

    public User getUserById(long id) {
        bucketService = BucketService.getInstance();
        String name = null;
        try {
            ConnectBaseService.connect();
            ResultSet rs = ConnectBaseService.statement.executeQuery("" +
                    "SELECT name " +
                    "FROM users " +
                    "WHERE id = '" + id + "';");
            rs.next();
            name = rs.getString("name");
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Start log. " + e);
        } finally {
            ConnectBaseService.disconnect();
        }
        return new User(id, name, bucketService.getBucketByUserId(id));

    }
}
