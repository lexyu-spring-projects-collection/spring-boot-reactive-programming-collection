package com.lex.practice.domain;

/**
 * @author : LEX_YU
 * @date : 2023/4/4
 */
public class BookInfo {
    private Long bookId;
    private String title;
    private String author;
    private String ISBN;

    public BookInfo() {
    }

    public BookInfo(Long bookId, String title, String author, String ISBN) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
