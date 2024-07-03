package com.example.webbancafev3.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String name;
    private double Price;
    private String Description;
    @ManyToOne
    @JoinColumn(name = "category_ID")
    private Category category;
    private String ImagePath;
    @Transient
    private MultipartFile imageFile;
    private Boolean Active = true;
}
