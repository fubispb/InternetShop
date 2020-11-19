package internet_shop.servlets;

import internet_shop.model.User;
import internet_shop.service.BucketService;
import internet_shop.service.ProductService;
import internet_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bucket")
public class BucketServlet {

    @Autowired
    private BucketService bucketService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String getList(Model model){
        User user = userService.getUserById(1);
        model.addAttribute("bucket", user.getBucket());
        return "bucket";
    }

    @PostMapping
    public String index(Long id){
        User user = userService.getUserById(1);
        bucketService.removeProductFromBucketByProductId(user.getId(), id);
        userService.deleteProductFromBucketByName(user, productService.getProductById(id));
        return "redirect:bucket";
    }

}
