package com.example.webbancafev3.Services;

import com.example.webbancafev3.Models.Category;
import com.example.webbancafev3.Repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService
{
    private final CategoryRepository categoryRepository;
    public List<Category> GetAllCategories()
    {
        return categoryRepository.findAll();
    }
    public Optional<Category> GetCategoryByID(Long ID)
    {
        return categoryRepository.findById(ID);
    }
    public void AddCategory(Category category)
    {
        categoryRepository.save(category);
    }
    public void UpdateCategory(@NotNull Category category)
    {
        Category ExistingCategory = categoryRepository.findById(category.getID()).orElseThrow(() -> new IllegalStateException("Category with ID " + category.getID() + " does not exist."));
        ExistingCategory.setName(category.getName());
        categoryRepository.save(ExistingCategory);
    }
    public void DeleteCategoryByID(Long ID) {
        if (!categoryRepository.existsById(ID))
        {
            throw new IllegalStateException("Category with ID " + ID + " does not exist.");
        }
        categoryRepository.deleteById(ID);
    }
}

