package am.shoppingapp.service;

import am.shoppingapp.entity.Category;
import am.shoppingapp.entity.Product;
import am.shoppingapp.entity.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    public AdminServiceImpl(UserService userService, CategoryService categoryService, ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void blockUser(Long userId, boolean block) throws EntityNotFoundException {
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBlocked(block);
            userService.save(user);
        } else {
            throw new EntityNotFoundException(String.format("User by %s id not found!", userId));
        }
    }

    @Override
    public void updateProductCategory(Long productId, Long categoryId) throws EntityNotFoundException {
        Optional<Product> optionalProduct = productService.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new EntityNotFoundException(String.format("Product by %s Id not found!", productId));
        }
        Optional<Category> optionalCategory = categoryService.findById(categoryId);
        if (!optionalCategory.isPresent()) {
            throw new EntityNotFoundException(String.format("Category by %s Id not found!", categoryId));
        }
        Product product = optionalProduct.get();
        product.setCategory(optionalCategory.get());
        productService.save(product);
    }
}
