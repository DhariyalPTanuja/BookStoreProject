package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.BookDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Book_info")
public @Data class BookModel {
//    @EmbeddedId
//    private BookId bookID;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private Double price;
    private int quantity;

    public BookModel() {
    }

    public void updateBookModel(BookDTO bookDTO) {
        this.bookName = bookDTO.bookName;
        this.authorName =  bookDTO.authorName;
        this.bookDescription =  bookDTO.bookDescription;
        this.bookImg = bookDTO. bookImg;
        this.price = bookDTO. price;
        this.quantity =  bookDTO.quantity;
    }
    public BookModel(BookDTO bookDTO){
        this.updateBookModel(bookDTO);
    }

}
