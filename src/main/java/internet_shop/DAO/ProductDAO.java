package internet_shop.DAO;

import internet_shop.mapper.ProductMapper;
import internet_shop.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository
@Transactional(isolation= Isolation.REPEATABLE_READ, propagation= Propagation.REQUIRED)
public class ProductDAO extends JdbcDaoSupport {


    @Autowired
    public ProductDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Product> getGeneralProductList() {
        String sql = ProductMapper.BASE_SQL;
        Object[] params = new Object[] {};
        ProductMapper mapper = new ProductMapper();
        return this.getJdbcTemplate().query(sql, params, mapper);
    }

    }
