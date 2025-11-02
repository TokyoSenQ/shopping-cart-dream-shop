package jeka.tsen.shopping_cart_dream_shop.service.category;

import jeka.tsen.shopping_cart_dream_shop.model.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategorById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategory(Long id);
}
