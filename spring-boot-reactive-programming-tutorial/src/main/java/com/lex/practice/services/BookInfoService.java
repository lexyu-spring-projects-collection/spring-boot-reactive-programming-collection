package com.lex.practice.services;

import com.lex.practice.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author : LEX_YU
 * @date : 2023/4/4
 */
public class BookInfoService {

    public Flux<BookInfo> getBooks() {
        var books = List.of(
                new BookInfo(1L, "Book One", "Author One", "123-456-789-123-1"),
                new BookInfo(2L, "Book Two", "Author Two", "321-654-987-321-2"),
                new BookInfo(3L, "Book Three", "Author Three", "987-321-654-987-3")
        );

        return Flux.fromIterable(books);
    }


    public Mono<BookInfo> getBookById(Long bookId) {
        var book = new BookInfo(1L, "Book One", "Author One", "123-456-789-123-1");

        return Mono.just(book);
    }
}
