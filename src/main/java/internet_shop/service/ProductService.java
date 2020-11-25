package internet_shop.service;

import internet_shop.DAO.ProductDAO;
import internet_shop.entity.ProductEntity;
import internet_shop.model.Product;

import java.util.List;
import java.util.Objects;

public class ProductService {

    private ConnectBaseService connectBaseService;
    private ProductDAO productDAO;
    private List<Product> generalProductList;

    public ProductService(ConnectBaseService connectBaseService, ProductDAO productDAO) {
        this.connectBaseService = connectBaseService;
        this.productDAO = productDAO;
    }

    public List<Product> getProducts() {
        if (Objects.isNull(generalProductList)) generalProductList = productDAO.getGeneralProductList();
        return generalProductList;
    }

    public Product getProductById(long id) {
        for (Product product : generalProductList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

}
