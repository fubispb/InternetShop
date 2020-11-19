package internet_shop.service;

import internet_shop.DAO.BucketDAO;
import internet_shop.model.Product;

import java.util.Map;

public class BucketService {

    private BucketDAO bucketDAO;
    private ConnectBaseService connectBaseService;
    private ProductService productService;


    public BucketService(ConnectBaseService connectBaseService, ProductService productService, BucketDAO bucketDAO) {
        this.connectBaseService = connectBaseService;
        this.productService = productService;
        this.bucketDAO = bucketDAO;
    }

    public Map<Product, Integer> getBucketByUserId(long id) {
        return bucketDAO.getUserBucketById(id);
    }

    public void insertInBucketByProductId(long id, int count) {
        bucketDAO.insertInUserBucket(id, count);
    }

    public void removeProductFromBucketByProductId(long idUser, long idProduct) {
        bucketDAO.deleteFromUserBucket(idUser, idProduct);
    }
}
