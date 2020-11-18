package internet_shop.servlets;

import internet_shop.model.User;
import internet_shop.service.BucketService;
import internet_shop.service.ProductService;
import internet_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/bucket")
public class BucketServlet extends HttpServlet {

    private static final long serialVersionUID = 1337762600257138628L;
    @Autowired
    private BucketService bucketService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUserById(1);
        req.setAttribute("bucket", user.getBucket());
        req.getRequestDispatcher("WEB-INF/view/bucket.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = userService.getUserById(1);
        long id = Long.parseLong(req.getParameter("id"));
        bucketService.removeProductFromBucketByProductId(user.getId(), id);
        userService.deleteProductFromBucketByName(user, productService.getProductById(id));
        resp.sendRedirect("bucket");
    }


}
