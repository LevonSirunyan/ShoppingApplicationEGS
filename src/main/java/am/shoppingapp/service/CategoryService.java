package am.shoppingapp.service;

import am.shoppingapp.entity.Category;
import am.shoppingapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    Category save(Category category) {
        return categoryRepository.save(category);
    }
}
