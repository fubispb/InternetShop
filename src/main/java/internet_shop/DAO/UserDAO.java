package internet_shop.DAO;

import internet_shop.model.User;
import internet_shop.service.BucketService;
import internet_shop.service.ConnectBaseService;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;

@Slf4j
public class UserDAO {

    private BucketService bucketService;
    private ConnectBaseService connectBaseService;

    public UserDAO(ConnectBaseService connectBaseService, BucketService bucketService) {
        this.bucketService = bucketService;
        this.connectBaseService = connectBaseService;
    }

    public User getUserById(long id) {
        String name = null;
        try {
            connectBaseService.connect();
            ResultSet rs = connectBaseService.getStatement().executeQuery("" +
                    "SELECT name " +
                    "FROM users " +
                    "WHERE id = '" + id + "';");
            rs.next();
            name = rs.getString("name");
        } catch (Exception e) {
            log.error("Start log. " + e);
        } finally {
            connectBaseService.disconnect();
        }
        return new User(id, name, bucketService.getBucketByUserId(id));

    }
}
