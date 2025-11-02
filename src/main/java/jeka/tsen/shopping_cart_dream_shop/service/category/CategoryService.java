package jeka.tsen.shopping_cart_dream_shop.service.category;

import jeka.tsen.shopping_cart_dream_shop.exceptions.AlreadyExistsExcpetion;
import jeka.tsen.shopping_cart_dream_shop.exceptions.ProductNotFoundExcpetion;
import jeka.tsen.shopping_cart_dream_shop.exceptions.ResourseNotFoundException;
import jeka.tsen.shopping_cart_dream_shop.model.Category;
import jeka.tsen.shopping_cart_dream_shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategorById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new AlreadyExistsExcpetion(category.getName()+ "already exists"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategorById(id)).map(oldCategory -> {
            oldCategory.setName(category.getName());
            return categoryRepository.save(oldCategory);
        })
            .orElseThrow(() -> new ResourseNotFoundException("Category not found"));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete, () -> {
            throw new ProductNotFoundExcpetion("Category not found");
        });
    }
}
