package jmaster.controller;

import jmaster.model.OrderDTO;
import jmaster.model.OrderItemDTO;
import jmaster.model.ProductDTO;
import jmaster.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    ProductService productService;

    @PostMapping("/order/add-to-cart")
    public String addToCart(
            HttpServletRequest request,
            @RequestParam(name = "product_id") Long product_id,
            HttpSession session
    ) {
        ProductDTO productDTO = productService.get(product_id);

        if (session.getAttribute("cart") == null) {
            OrderDTO orderDTO = new OrderDTO();
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setQuantity(1);
            orderItemDTO.setProductDTO(productDTO);

            List<OrderItemDTO> orderItemDTOS = new ArrayList<OrderItemDTO>();
            orderItemDTOS.add(orderItemDTO);
            orderDTO.setOrderItemDTOS(orderItemDTOS);
            session.setAttribute("cart", orderDTO);
        } else {
            OrderDTO orderDTO = (OrderDTO) session.getAttribute("cart");
            List<OrderItemDTO> orderItemDTOS = orderDTO.getOrderItemDTOS();
            boolean itemNotFound = true;
            for (OrderItemDTO orderItemDTO : orderItemDTOS) {
                if (orderItemDTO.getProductDTO().getId().equals(productDTO.getId())) {
                    orderItemDTO.setQuantity(orderItemDTO.getQuantity() + 1);
                    itemNotFound = false;
                }
            }

            if (itemNotFound) {
                OrderItemDTO orderItemDTO = new OrderItemDTO();
                orderItemDTO.setQuantity(1);
                orderItemDTO.setProductDTO(productDTO);
                orderItemDTOS.add(orderItemDTO);
            }
            session.setAttribute("cart", orderDTO);
        }
        return "redirect:/product/products";
    }


    @GetMapping("/order/delete")
    public String deleteItem(
            HttpSession session,
            @RequestParam(name = "product_id") Long product_id
    ) {
        ProductDTO productDTO = productService.get(product_id);
        OrderDTO orderDTO = (OrderDTO) session.getAttribute("cart");
        List<OrderItemDTO> orderItemDTOS = orderDTO.getOrderItemDTOS();//gan gia tri ket noi vs doi tuong
//        for (OrderItemDTO orderItemDTO : orderItemDTOS) {
//            if (orderItemDTO.getProductDTO().getId().equals(productDTO.getId())) {
//                orderItemDTOS.remove(orderItemDTO);
//            }
//        }

        //logger orderDTO.size()
        orderItemDTOS.removeIf(orderItemDTO -> orderItemDTO.getProductDTO().getId().equals(productDTO.getId()));
        //logger orderDTO.size() - 1
        //tu dong thay doi gia tri orderDTO vs session ko can set lai, do da gan gia tri ket noi voi doi tuong
        return "redirect:/view-cart";
    }


    @GetMapping("/view-cart")
    public String viewCart(Model model, HttpSession session) {
        if (session.getAttribute("cart") != null) {
            OrderDTO orderDTO = (OrderDTO) session.getAttribute("cart");
            model.addAttribute("cart", orderDTO.getOrderItemDTOS());
        }
        return "product/view-cart";
    }
}
