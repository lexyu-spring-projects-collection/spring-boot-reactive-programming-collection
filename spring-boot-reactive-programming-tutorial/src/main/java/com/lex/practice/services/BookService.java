package com.lex.practice.services;

import com.lex.practice.domain.Book;
import com.lex.practice.domain.Review;
import com.lex.practice.exception.BookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.util.List;

/**
 * @author : LEX_YU
 * @date : 2023/4/4
 */
public class BookService {
    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    private BookInfoService bookInfoService;
    private ReviewService reviewService;

    public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
        this.bookInfoService = bookInfoService;
        this.reviewService = reviewService;
    }

    public Flux<Book> getBooks() {
        var allBooks = bookInfoService.getBooks();

        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews =
                            reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews.map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is : " + throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .log();
    }

    public Flux<Book> getBooksRetry() {
        var allBooks = bookInfoService.getBooks();

        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews =
                            reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews.map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is : " + throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .retry(3)
                .log();
    }

    public Flux<Book> getBooksRetryWhen() {

        var retrySpecs = getRetryBackoffSpec();

        var allBooks = bookInfoService.getBooks();

        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews =
                            reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews.map(review -> new Book(bookInfo, review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is : " + throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .retryWhen(retrySpecs)
                .log();
    }

    private static RetryBackoffSpec getRetryBackoffSpec() {
        return Retry
                .backoff(3, Duration.ofSeconds(1))
                .filter(throwable -> throwable instanceof BookException)
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> Exceptions.propagate(retrySignal.failure()));
    }

    public Mono<Book> getBookById(Long bookId) {
        var book = bookInfoService.getBookById(bookId);
        var review = reviewService.getReviews(bookId).collectList();

        return book
                .zipWith(review, (b, r) -> new Book(b, r))
                .log();

    }

}
