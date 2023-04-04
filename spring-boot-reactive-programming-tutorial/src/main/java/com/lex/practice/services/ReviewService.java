package com.lex.practice.services;

import com.lex.practice.domain.Review;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author : LEX_YU
 * @date : 2023/4/4
 */
public class ReviewService {

    public Flux<Review> getReviews(Long bookId) {
        var reviewList = List.of(
                new Review(1L, bookId, 9.1, "Good Book"),
                new Review(2L, bookId, 9.1, "Worth Reading")
        );

        return Flux.fromIterable(reviewList);
    }
}
