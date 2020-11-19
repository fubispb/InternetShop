package internet_shop.service;

import internet_shop.DAO.UserDAO;
import internet_shop.model.Product;
import internet_shop.model.User;

public class UserService {

    private UserDAO userDAO;
    private ConnectBaseService connectBaseService;
    private BucketService bucketService;

    public UserService(ConnectBaseService connectBaseService, BucketService bucketService, UserDAO userDAO) {
        this.connectBaseService = connectBaseService;
        this.bucketService = bucketService;
        this.userDAO = userDAO;
    }

    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    public void deleteProductFromBucketByName(User user, Product product) {
        user.removeFromBucketByProduct(product);
    }

}
