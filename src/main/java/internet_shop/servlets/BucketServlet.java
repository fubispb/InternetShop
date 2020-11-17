package internet_shop.servlets;

import internet_shop.model.User;
import internet_shop.service.BucketService;
import internet_shop.service.ProductService;
import internet_shop.service.UserService;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class BucketServlet extends HttpServlet {

    private static final long serialVersionUID = 1337762600257138628L;
    private BucketService bucketService;
    private ProductService productService;
    private UserService userService;
    private User user;

    public BucketServlet() {
    }

    public BucketServlet(BucketService bucketService, ProductService productService, UserService userService) {
        this.userService = userService;
        this.bucketService = bucketService;
        this.productService = productService;
        user = userService.getUserById(1);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("bucket", user.getBucket());
        req.getRequestDispatcher("WEB-INF/view/bucket.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        bucketService.removeProductFromBucketByProductId(user.getId(), id);
        userService.deleteProductFromBucketByName(user, productService.getProductById(id));
        resp.sendRedirect("bucket");
    }


}
