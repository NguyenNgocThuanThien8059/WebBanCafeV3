package com.example.webbancafev3.Controllers;

import com.example.webbancafev3.Models.Product;
import com.example.webbancafev3.Services.CategoryService;
import com.example.webbancafev3.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.validation.Valid;

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
    public String AddProduct(@Valid Product product, BindingResult Result)
    {
        if(Result.hasErrors())
        {
            return "products/add-product";
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
    public String UpdateProduct(@PathVariable Long ID, @Valid Product product, BindingResult Result)
    {
        if(Result.hasErrors())
        {
            product.setID(ID);
            return "products/update-product";
        }
        productService.UpdateProduct(product);
        return "redirect:/products";
    }
    @GetMapping("/delete/{ID}")
    public String DeleteProduct(@PathVariable Long ID)
    {
        productService.DeleteProductByID(ID);
        return "redirect:/products";
    }
}