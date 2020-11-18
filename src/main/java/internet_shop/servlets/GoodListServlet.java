package internet_shop.servlets;

import internet_shop.model.Product;
import internet_shop.service.BucketService;
import internet_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public String index(Model model){
        Integer count = (Integer) model.getAttribute("count");
        Long id = (Long) model.getAttribute("id");
        System.out.println(id);
        System.out.println(count);
        bucketService.insertInBucketByProductId(id, count);
        return "goodlist";
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("goodlistservlet");
//        System.out.println(productService);
//        System.out.println(productService.getProductById(1));
//        System.out.println(productService.getProducts());
//        List<Product> listOfProducts = productService.getProducts();
//        System.out.println("goodlistservlet2" + listOfProducts);
//        req.setAttribute("product", listOfProducts);
//        System.out.println("goodlistservlet3");
//        req.getRequestDispatcher("WEB-INF/view/goodlist.jsp").forward(req, resp);
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        int count = Integer.parseInt(req.getParameter("count"));
//        long id = Long.parseLong(req.getParameter("id"));
//        bucketService.insertInBucketByProductId(id, count);
//        resp.sendRedirect("goodlist");
//    }
}
