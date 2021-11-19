package com.example.storeratingservice.controller;

import com.example.storeratingservice.entity.Rating;
import com.example.storeratingservice.entity.UserRating;
import com.example.storeratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{productId}")
    public Optional<Rating> getRating(@PathVariable("productId") Long productId) {
        return ratingService.getRating(productId) ;
    }

    @GetMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") Long userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating(1L, 4.0),
                new Rating(2L, 3.0)
        );

        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return  userRating;
    }


    @GetMapping("product/{productId}")
    public ResponseEntity<?> getUserByProfileId(@PathVariable("productId") Long productId) {
//        return ResponseEntity.ok(productId);
        return ResponseEntity.ok(ratingService.getProductById(productId));
    }
}
