package am.shoppingapp.controller;

import am.shoppingapp.entity.Product;
import am.shoppingapp.enums.RateScore;
import am.shoppingapp.service.RegularUserService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class RegularUserController {

    private final RegularUserService regularUserService;

    public RegularUserController(RegularUserService regularUserService) {
        this.regularUserService = regularUserService;
    }

    @PostMapping("/rateAndComment")
    public ResponseEntity<?> rateAndComment(@RequestParam Long productId, @RequestParam RateScore rateScore
            , @RequestParam String comment) {
        try {
            regularUserService.rateAndComment(productId, rateScore, comment);
            return ResponseEntity.ok("Rate and comment added successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

    @PostMapping("/findByLikeName")
    public ResponseEntity<?> findByLikeName(@RequestParam String productName) {
        Set<Product> productSet = regularUserService.findByLikeName(productName);
        return ResponseEntity.ok(productSet);
    }

    @PostMapping("/findByName")
    public ResponseEntity<?> findByName(@RequestParam String productName) {
        Optional<Product> optionalProduct = regularUserService.findByName(productName);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Product by %s Name not found", productName));
    }

    @PostMapping("/findByPriceRange")
    public ResponseEntity<?> findByPriceRange(@RequestParam Float from, @RequestParam Float to) {
        Set<Product> productSet = regularUserService.findByPriceRange(from, to);
        return ResponseEntity.ok(productSet);
    }

    @PostMapping("/findByRateRange")
    public ResponseEntity<?> findByRateRange(@RequestParam RateScore from, @RequestParam RateScore to) {
        if (from.ordinal() > to.ordinal()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Rate range interval is not valid");
        }
        Set<Product> productSet = regularUserService.findByRateRange(from, to);
        return ResponseEntity.ok(productSet);
    }
}
