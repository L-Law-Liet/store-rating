package com.example.storeratingservice.service;

import com.example.storeratingservice.entity.Product;
import com.example.storeratingservice.entity.Rating;
import com.example.storeratingservice.repository.RatingRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Optional<Rating> getRating(Long id) {
        return ratingRepository.findById(id);
    }

    @HystrixCommand(
            fallbackMethod = "getProductByIdFallback"
    )
    public Product getProductById(Long id) {
        return restTemplate.getForObject("http://store-information-service/products/" + id, Product.class);
    }

    public Product getProductByIdFallback(Long id) {
        Product product = new Product();
        product.setDescription("Product is not available: Service Unavailable");
        product.setTitle("Unknown");
        return product;
    }
}
