package com.example.storeratingservice.service;

import com.example.storeratingservice.entity.Product;
import com.example.storeratingservice.entity.Rating;
import com.example.storeratingservice.repository.RatingRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
            fallbackMethod = "getProductByIdFallback",
            threadPoolKey = "getProductById",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="40"),
                    @HystrixProperty(name="maxQueueSize", value="20"),
            })
    public Product getProductById(Long id) {
        String apiCredentials = "rest-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        HttpEntity<String> entity = new HttpEntity<>(headers);

//        return restTemplate.getForObject("http://store-information-service/products/" + id, Product.class);
        return restTemplate.exchange("http://store-information-service/products/" + id,
                HttpMethod.GET, entity, Product.class).getBody();
    }

    public Product getProductByIdFallback(Long id) {
        Product product = new Product();
        product.setDescription("Product is not available: Service Unavailable");
        product.setTitle("Unknown");
        return product;
    }
}
