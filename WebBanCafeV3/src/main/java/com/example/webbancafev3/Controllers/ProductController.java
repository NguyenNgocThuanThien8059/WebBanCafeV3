package com.example.webbancafev3.Controllers;

import com.example.webbancafev3.Models.Product;
import com.example.webbancafev3.Services.CategoryService;
import com.example.webbancafev3.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController
{
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String ShowProductList(Model model)
    {
        model.addAttribute("products", productService.GetAllProducts());
        return "products/product-list";
    }
    @GetMapping("/add")
    public String ShowAddForm(Model model)
    {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.GetAllCategories());
        return "products/add-product";
    }
    @PostMapping("/add")
    public String AddProduct(@Valid Product product, BindingResult Result, @RequestParam("imageFile") MultipartFile imageFile)
    {
        if(Result.hasErrors())
        {
            return "products/add-product";
        }
        if(imageFile != null && !imageFile.isEmpty())
        {
            try
            {
                String fileName = UUID.randomUUID() + ".png";
                Path path = Paths.get("src/main/resources/static/productimages/" + fileName);
                Files.copy(imageFile.getInputStream(), path);
                product.setImagePath("/productimages/" + fileName);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        productService.AddProduct(product);
        return "redirect:/products";
    }
    @GetMapping("/edit/{ID}")
    public String ShowEditForm(@PathVariable Long ID, Model model)
    {
        Product product = productService.GetProductByID(ID)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + ID));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.GetAllCategories());
        return "products/update-product";
    }
    @PostMapping("/update/{ID}")
    public String UpdateProduct(@PathVariable Long ID, @Valid Product product, BindingResult Result, @RequestParam(name = "imageFile") MultipartFile imageFile)
    {
        if(Result.hasErrors())
        {
            product.setID(ID);
            return "products/update-product";
        }
        if (imageFile != null && !imageFile.isEmpty())
        {
            try {
                String fileName = UUID.randomUUID() + ".png";
                Path path = Paths.get("src/main/resources/static/images/" + fileName);
                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                product.setImagePath("/images/" + fileName);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        productService.UpdateProduct(product, imageFile);
        return "redirect:/products";
    }
    @GetMapping("/delete/{ID}")
    public String DeleteProduct(@PathVariable Long ID)
    {
        productService.DeleteProductByID(ID);
        return "redirect:/products";
    }
    @GetMapping("/search")
    public String searchProducts(@RequestParam("name") String name, Model model) {
        List<Product> products = productService.findProductsByNameContaining(name);
        model.addAttribute("products", products);
        return "products/product-list";
    }
}