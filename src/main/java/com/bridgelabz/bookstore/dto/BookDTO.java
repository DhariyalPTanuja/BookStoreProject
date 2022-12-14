package com.bridgelabz.bookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookDTO {
    @NotBlank(message = "Book Name not empty")
    public String bookName;
    @NotBlank(message = "author name not empty")
    public String authorName;
    @NotBlank(message = "Book Description not empty")
    public String bookDescription;
    public String bookImg;
    @NotNull(message = "price not null")
    public Double price;
    @NotNull(message = "quantity not null")
    public int quantity;

    public BookDTO(String bookName, String authorName, String bookDescription, String bookImg, Double price, int quantity) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookDescription = bookDescription;
        this.bookImg = bookImg;
        this.price = price;
        this.quantity = quantity;
    }
}
