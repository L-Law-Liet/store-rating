package com.example.storeratingservice.service;

import com.example.storeratingservice.entity.Rating;
import com.example.storeratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Optional<Rating> getRating(Long id) {
        return ratingRepository.findById(id);
    }
}
