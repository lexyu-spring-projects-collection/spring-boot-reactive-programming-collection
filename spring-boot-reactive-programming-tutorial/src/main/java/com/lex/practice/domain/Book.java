package com.lex.practice.domain;

import java.util.List;

/**
 * @author : LEX_YU
 * @date : 2023/4/4
 */
public class Book {
    private BookInfo bookInfo;
    private List<Review> reviews;

    public Book() {
    }

    public Book(BookInfo bookInfo, List<Review> reviews) {
        this.bookInfo = bookInfo;
        this.reviews = reviews;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookInfo=" + bookInfo +
                ", reviews=" + reviews +
                '}';
    }
}
