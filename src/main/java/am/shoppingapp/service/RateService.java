package am.shoppingapp.service;

import am.shoppingapp.entity.Product;
import am.shoppingapp.entity.Rate;
import am.shoppingapp.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RateService {

    private final RateRepository rateRepository;

    @Autowired
    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    Rate save(Rate rate) {
        return rateRepository.save(rate);
    }

    public Set<Product> findByRateRange(int from, int to) {
        return rateRepository.findByRateBetween(from, to);
    }
}
