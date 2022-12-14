package com.bridgelabz.bookstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.io.Serializable;

@Embeddable
public @Data class BookId implements Serializable {
    @Column(name = "book_code" , columnDefinition ="varchar(10) default 'Book-'")
    private String bookCode;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    public BookId() {
    }

    public BookId(String bookCode, Long bookId) {
        this.bookCode = bookCode;
        this.bookId = bookId;
    }
}
