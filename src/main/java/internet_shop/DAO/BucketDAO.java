package internet_shop.DAO;

import internet_shop.entity.BucketEntity;
import internet_shop.entity.ProductEntity;
import internet_shop.model.Product;
import internet_shop.service.ConnectBaseService;
import internet_shop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@Component
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class BucketDAO {

    private ProductService productService;
    private ConnectBaseService connectBaseService;
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public BucketDAO(ProductService productService,
                     ConnectBaseService connectBaseService,
                     EntityManagerFactory entityManagerFactory)
    {
        this.productService = productService;
        this.connectBaseService = connectBaseService;
        this.entityManagerFactory = entityManagerFactory;
    }

    public Map<Product, Integer> getUserBucketById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<BucketEntity> list = em.createQuery("" +
                "from BucketEntity b " +
                "where b.users_id = :id " +
                "group by b.products_id")
                .setParameter("id", id)
                .getResultList();
        Map<Product, Integer> bucket = new TreeMap<>();
        for (BucketEntity o : list) {
            bucket.put(productService.getProductById(o.getProducts_id()), o.getCount());
        }
        em.close();
        return bucket;
    }

    public void deleteFromUserBucket(long idUser, long idProduct) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete BucketEntity b where b.users_id = :idUser and b.products_id = :idProduct")
                .setParameter("idUser", idUser)
                .setParameter("idProduct", idProduct)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void insertInUserBucket(long id, int count) {
        EntityManager em = entityManagerFactory.createEntityManager();
        BucketEntity entity = new BucketEntity();
        entity.setUsers_id(1L);
        entity.setProducts_id(id);
        entity.setCount(count);
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

}
