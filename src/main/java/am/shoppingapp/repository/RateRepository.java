package am.shoppingapp.repository;

import am.shoppingapp.entity.Product;
import am.shoppingapp.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    @Query("SELECT distinct r.product FROM Rate r WHERE r.value BETWEEN :from AND :to")
    Set<Product> findByRateBetween(@Param("from") int from, @Param("to") int to);
}
