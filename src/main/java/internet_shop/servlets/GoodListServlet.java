package internet_shop.servlets;

import internet_shop.model.Product;
import internet_shop.service.BucketService;
import internet_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("goodlist")
public class GoodListServlet {

    @Autowired
    private ProductService productService;
    @Autowired
    private BucketService bucketService;

    @GetMapping
    public String getList(Model model){
        List<Product> listOfProducts = productService.getProducts();
        model.addAttribute("product", listOfProducts);
        return "goodlist";
    }

    @PostMapping
    public String index(Long id, Integer count){
        bucketService.insertInBucketByProductId(id, count);
        return "redirect:goodlist";
    }

}
