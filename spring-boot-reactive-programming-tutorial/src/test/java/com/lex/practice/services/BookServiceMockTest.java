package com.lex.practice.services;

import com.lex.practice.exception.BookException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : LEX_YU
 * @date : 2023/4/4
 */
@ExtendWith(MockitoExtension.class)
class BookServiceMockTest {

    @Mock
    private final BookInfoService bookInfoService = new BookInfoService();
    @Mock
    private final ReviewService reviewService = new ReviewService();
    @InjectMocks
    private BookService bookService;

    @Test
    void getBooksMock() {
        Mockito.when(bookInfoService.getBooks())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenCallRealMethod();

        var books = bookService.getBooks();
        StepVerifier
                .create(books)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void getBooksMockOnError() {
        Mockito.when(bookInfoService.getBooks())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenThrow(new IllegalStateException("Exception using test"));

        var books = bookService.getBooks();
        StepVerifier
                .create(books)
                .expectError(BookException.class)
                .verify();
    }

    @Test
    void getBooksMockOnErrorRetry() {
        Mockito.when(bookInfoService.getBooks())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenThrow(new IllegalStateException("Exception using test"));

        var books = bookService.getBooksRetry();
        StepVerifier
                .create(books)
                .expectError(BookException.class)
                .verify();
    }

    @Test
    void getBooksMockOnErrorRetryWhen() {
        Mockito.when(bookInfoService.getBooks())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenThrow(new IllegalStateException("Exception using test"));

        var books = bookService.getBooksRetryWhen();
        StepVerifier
                .create(books)
                .expectError(BookException.class)
                .verify();
    }
}