package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.CartDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Cart_info")
public @Data class CartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_Id")
    private  Long cartID;

    @OneToOne
    @JoinColumn(name = "user_Id")
    private UserModel userID;
    @ManyToOne
    @JoinColumn(name = "book_Id")
    private BookModel bookID;
    private int cartQuantity;

    public CartModel() {
    }


    public void  updateCartModel(CartDTO cartDTO) {
        this.userID = cartDTO.userID;
        this.bookID =  cartDTO.bookID;
        this.cartQuantity =cartDTO.cartQuantity;
    }
    public CartModel(CartDTO cartDTO) {
        this.updateCartModel(cartDTO);
    }

}
