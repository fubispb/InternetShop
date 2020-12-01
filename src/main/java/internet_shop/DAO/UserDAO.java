package internet_shop.DAO;

import internet_shop.entity.UserEntity;
import internet_shop.service.BucketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Slf4j
@Repository
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class UserDAO extends JdbcDaoSupport {

    private BucketService bucketService;
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserDAO(DataSource dataSource, BucketService bucketService, EntityManagerFactory entityManagerFactory) {
        this.bucketService = bucketService;
        this.setDataSource(dataSource);
        this.entityManagerFactory = entityManagerFactory;
    }

    public UserEntity getUserById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(UserEntity.class, id);
    }

}
