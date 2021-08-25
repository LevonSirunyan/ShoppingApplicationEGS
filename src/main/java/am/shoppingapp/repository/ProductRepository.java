package am.shoppingapp.repository;

import am.shoppingapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    Set<Product> findByNameIsLike(@Param("name") String name);

    Set<Product> findByPriceBetween(Float priceFrom, Float priceTo);
}
