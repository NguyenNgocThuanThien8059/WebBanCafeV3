package com.example.webbancafev3.Services;

import com.example.webbancafev3.Models.CartItem;
import com.example.webbancafev3.Models.Order;
import com.example.webbancafev3.Models.OrderDetail;
import com.example.webbancafev3.Repositories.OrderRepository;
import com.example.webbancafev3.Repositories.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartService cartService;
    @Transactional
    public Order createOrder(String CustomerName, List<CartItem> cartItems)
    {
        Order order = new Order();
        order.setCustomerName(CustomerName);
        order = orderRepository.save(order);
        for (CartItem item : cartItems)
        {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
        }
        cartService.ClearCart();
        return order;
    }
}


