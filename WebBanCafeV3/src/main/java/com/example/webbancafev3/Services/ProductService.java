package com.example.webbancafev3.Services;

import com.example.webbancafev3.Models.Product;
import com.example.webbancafev3.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService
{
    private final ProductRepository productRepository;
    public List<Product> GetAllProducts()
    {
        return productRepository.findAll();
    }
    public Optional<Product> GetProductByID(Long ID)
    {
        return productRepository.findById(ID);
    }
    public Product AddProduct(Product product)
    {
        return productRepository.save(product);
    }
    public Product UpdateProduct(@NotNull Product product)
    {
        Product ExistingProduct = productRepository.findById(product.getID())
                .orElseThrow(() -> new IllegalStateException("Product with ID " + product.getID() + " does not exist"));
        ExistingProduct.setName(product.getName());
        ExistingProduct.setPrice(product.getPrice());
        ExistingProduct.setDescription(product.getDescription());
        ExistingProduct.setCategory(product.getCategory());
        return productRepository.save(ExistingProduct);
    }
    public void DeleteProductByID(Long ID)
    {
        if(!productRepository.existsById(ID))
        {
            throw new IllegalStateException("Product with ID " + ID + " does not exist");
        }
        productRepository.deleteById(ID);
    }
}

