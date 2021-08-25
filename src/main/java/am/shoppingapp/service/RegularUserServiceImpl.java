package am.shoppingapp.service;

import am.shoppingapp.entity.Product;
import am.shoppingapp.entity.Rate;
import am.shoppingapp.entity.User;
import am.shoppingapp.enums.RateScore;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
public class RegularUserServiceImpl implements RegularUserService {
    private final UserService userService;
    private final ProductService productService;
    private final RateService rateService;

    public RegularUserServiceImpl(UserService userService, ProductService productService, RateService rateService) {
        this.userService = userService;
        this.productService = productService;
        this.rateService = rateService;
    }

    @Override
    public void rateAndComment(Long productId, RateScore rateScore, String comment) throws MessagingException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.findByLogin(username);
        checkUser(user);
        Optional<Product> optionalProduct = productService.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new EntityNotFoundException(String.format("Product by %s Id not found!", productId));
        }
        Rate rate = Rate.builder().user(user).product(optionalProduct.get())
                .value(rateScore.ordinal() + 1).comment(comment).build();
        rateService.save(rate);
    }

    @Override
    public Set<Product> findByLikeName(String productName) {
        return productService.findByLikeName(productName);
    }

    @Override
    public Optional<Product> findByName(String productName) {
        return productService.findByName(productName);
    }

    @Override
    public Set<Product> findByPriceRange(Float from, Float to) {
        return productService.findByPriceRange(from, to);
    }

    @Override
    public Set<Product> findByRateRange(RateScore from, RateScore to) {
        return rateService.findByRateRange(from.ordinal() + 1, to.ordinal() + 1);
    }

    private void checkUser(User user) throws MessagingException {
        if (user.isBlocked()) {
            throw new MessagingException("Your user is blokced");
        }
    }
}
