package internet_shop.DAO;

import internet_shop.mapper.UserMapper;
import internet_shop.model.User;
import internet_shop.service.BucketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Slf4j
@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport {

    private BucketService bucketService;

    @Autowired
    public UserDAO(DataSource dataSource, BucketService bucketService) {
        this.bucketService = bucketService;
        this.setDataSource(dataSource);
    }

    public User getUserById(long id) {
        String sql = UserMapper.SQL_QUERY(id);
        Object[] params = new Object[] {};
        UserMapper mapper = new UserMapper(bucketService);
        return this.getJdbcTemplate().query(sql, params, mapper).get(0);
    }
}
