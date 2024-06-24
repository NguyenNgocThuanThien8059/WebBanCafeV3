package com.example.webbancafev3.Services;

import com.example.webbancafev3.Models.CartItem;
import com.example.webbancafev3.Models.Product;
import com.example.webbancafev3.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class CartService
{
    private List<CartItem> cartItems = new ArrayList<>();
    @Autowired
    private ProductRepository productRepository;
    public void AddToCart(Long ProductID, int Quantity)
    {
        Product product = productRepository.findById(ProductID).orElseThrow(() -> new IllegalArgumentException("Product not found: " + ProductID));
        cartItems.add(new CartItem(product, Quantity));
    }
    public List<CartItem> GetCartItems()
    {
        return cartItems;
    }
    public void RemoveFromCart(Long ProductID)
    {
        cartItems.removeIf(item -> item.getProduct().getID().equals(ProductID));
    }
    public void ClearCart()
    {
        cartItems.clear();
    }
}
