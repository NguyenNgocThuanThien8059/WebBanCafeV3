package com.example.webbancafev3.Controllers;

import com.example.webbancafev3.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController
{
    @Autowired
    private CartService cartService;
    @GetMapping
    public String ShowCart(Model model)
    {
        model.addAttribute("cartItems", cartService.GetCartItems());
        return "/cart/cart";
    }
    @PostMapping("/add")
    public String AddToCart(@RequestParam Long ProductID, @RequestParam int Quantity)
    {
        cartService.AddToCart(ProductID, Quantity);
        return "redirect:/cart";
    }
    @GetMapping("/remove/{ProductID}")
    public String RemoveFromCart(@PathVariable Long ProductID)
    {
        cartService.RemoveFromCart(ProductID);
        return "redirect:/cart";
    }
    @GetMapping("/clear")
    public String ClearCart()
    {
        cartService.ClearCart();
        return "redirect:/cart";
    }
}