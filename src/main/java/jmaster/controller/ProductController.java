package jmaster.controller;

import jmaster.model.ProductDTO;
import jmaster.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @GetMapping(value = "/product/products")
    public String searchUser(HttpServletRequest request) {

        List<ProductDTO> productList = productService.getAll();
        request.setAttribute("productList", productList);

        return "product/products";
    }

    @GetMapping(value = "/product/add")
    public String AdminAddUserGet() {
        return "product/add-product";
    }

    @PostMapping(value = "/product/add")
    public String AdminAddUserPost(@ModelAttribute(name = "addProduct") ProductDTO productDTO) {
        productService.insert(productDTO);
        return "redirect:/product/products";
    }
}
