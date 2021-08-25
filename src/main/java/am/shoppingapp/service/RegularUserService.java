package am.shoppingapp.service;

import am.shoppingapp.entity.Product;
import am.shoppingapp.enums.RateScore;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import java.util.Optional;
import java.util.Set;

public interface RegularUserService {
    void rateAndComment(Long productId, RateScore rateScore, String comment) throws MessagingException;

    Set<Product> findByLikeName(String productName);

    Optional<Product> findByName(String productName);

    Set<Product> findByPriceRange(Float from, Float to);

    Set<Product> findByRateRange(RateScore from, RateScore to);
}
