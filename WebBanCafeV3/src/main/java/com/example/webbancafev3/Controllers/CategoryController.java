package com.example.webbancafev3.Controllers;

import com.example.webbancafev3.Models.Category;
import com.example.webbancafev3.Services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController
{
    @Autowired
    private final CategoryService categoryService;
    @GetMapping("/categories/add")
    public String ShowAddForm(Model model)
    {
        model.addAttribute("category", new Category());
        return "/categories/add-category";
    }
    @PostMapping("/categories/add")
    public String AddCategory(@Valid Category category, BindingResult result)
    {
        if (result.hasErrors()) {
            return "/categories/add-category";
        }
        categoryService.AddCategory(category);
        return "redirect:/categories";
    }
    @GetMapping("/categories")
    public String ListCategories(Model model)
    {
        List<Category> categories = categoryService.GetAllCategories();
        model.addAttribute("categories", categories);
        return "/categories/category-list";
    }
}
