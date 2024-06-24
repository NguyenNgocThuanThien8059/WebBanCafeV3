package com.example.webbancafev3.Controllers;

import com.example.webbancafev3.Models.CartItem;
import com.example.webbancafev3.Models.Product;
import com.example.webbancafev3.Models.Order;
import com.example.webbancafev3.Services.CartService;
import com.example.webbancafev3.Services.OrderService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @GetMapping("/checkout")
    public String checkout()
    {
        return "/cart/checkout";
    }
    @PostMapping("/submit")
    public String submitOrder(String CustomerName) {
        List<CartItem> cartItems = cartService.GetCartItems();
        if (cartItems.isEmpty()) {
            return "redirect:/cart";
        }
        orderService.createOrder(CustomerName, cartItems);
        return "redirect:/order/confirmation";
    }
    @GetMapping("/confirmation")
    public String orderConfirmation(Model model)
    {
        model.addAttribute("message", "Your order has been successfully placed.");
        return "cart/order-confirmation";
    }
}
