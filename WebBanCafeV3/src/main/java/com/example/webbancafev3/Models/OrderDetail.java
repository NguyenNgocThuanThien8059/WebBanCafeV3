package com.example.webbancafev3.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private int Quantity;
    @ManyToOne
    @JoinColumn(name = "product_ID")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_ID")
    private Order order;
}
