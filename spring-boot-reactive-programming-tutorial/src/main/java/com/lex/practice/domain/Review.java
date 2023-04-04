package com.lex.practice.domain;

/**
 * @author : LEX_YU
 * @date : 2023/4/4
 */
public class Review {
    private Long reviewId;
    private Long bookId;
    private Double ratings;
    private String comments;

    public Review() {
    }

    public Review(Long reviewId, Long bookId, Double ratings, String comments) {
        this.reviewId = reviewId;
        this.bookId = bookId;
        this.ratings = ratings;
        this.comments = comments;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", bookId=" + bookId +
                ", ratings=" + ratings +
                ", comments='" + comments + '\'' +
                '}';
    }
}
