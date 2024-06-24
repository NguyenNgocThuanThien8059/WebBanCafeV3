package com.example.webbancafev3.Models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String CustomerName;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
