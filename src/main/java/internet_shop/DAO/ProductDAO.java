package internet_shop.DAO;

import internet_shop.entity.ProductEntity;
import internet_shop.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class ProductDAO extends JdbcDaoSupport {

    List<Product> modelProductsList;
    EntityManagerFactory entityManagerFactory;

    @Autowired
    public ProductDAO(DataSource dataSource, EntityManagerFactory entityManagerFactory) {
        this.setDataSource(dataSource);
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Product> getGeneralProductList() {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> productCriteria = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> productRoot = productCriteria.from(ProductEntity.class);
        productCriteria.select(productRoot);
        List<ProductEntity> entityList = em.createQuery(productCriteria).getResultList();
        modelProductsList = new ArrayList<>();
        for (ProductEntity products : entityList) {
            modelProductsList.add(new Product(products.getId(), products.getName(), products.getPrice(), products.getCountry().getName()));
        }
        return modelProductsList;

    }

}
