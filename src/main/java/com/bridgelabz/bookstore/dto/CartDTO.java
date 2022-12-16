package com.bridgelabz.bookstore.dto;

import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.model.UserModel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public  class CartDTO {

    public BookModel bookID;
    public UserModel userID;
    @NotNull(message = "quantity not null")
    public int cartQuantity;


    public CartDTO(UserModel userId, BookModel bookId, int cartQuantity) {
        this.userID = userId;
        this.bookID = bookId;
        this.cartQuantity = cartQuantity;
    }
}
