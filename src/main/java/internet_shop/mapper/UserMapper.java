package internet_shop.mapper;

import internet_shop.model.User;
import internet_shop.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User>{


    private BucketService bucketService;

    @Autowired
    public UserMapper(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    public static String SQL_QUERY(long id) {
        return "SELECT id, name FROM users WHERE id ='" + id + "';";
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong(1);
        String name = rs.getString(2);
        return new User(id, name, bucketService.getBucketByUserId(id));
    }
}
