package internet_shop.service;

import internet_shop.DAO.ProductDAO;
import internet_shop.model.Product;

import java.util.List;

public class ProductService {

    private ConnectBaseService connectBaseService;
    private ProductDAO productDAO;
    private List<Product> generalProductList;

    public ProductService(ConnectBaseService connectBaseService) {
        this.connectBaseService = connectBaseService;
        productDAO = new ProductDAO(connectBaseService);
        this.generalProductList = productDAO.getGeneralProductList();
    }

    public List<Product> getProducts() {
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
