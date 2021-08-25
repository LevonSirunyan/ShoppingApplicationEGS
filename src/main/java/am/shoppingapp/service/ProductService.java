package am.shoppingapp.service;

import am.shoppingapp.entity.Product;
import am.shoppingapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    Product save(Product product) {
        return productRepository.save(product);
    }

    public Set<Product> findByLikeName(String productName) {
        return productRepository.findByNameIsLike(productName);
    }

    public Optional<Product> findByName(String productName) {
        return productRepository.findByName(productName);
    }

    public Set<Product> findByPriceRange(Float from, Float to) {
        return productRepository.findByPriceBetween(from, to);
    }
}
