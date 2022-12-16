package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.model.CartModel;

import java.util.List;

public interface ICartService {

    public CartModel insert(CartDTO cartDTO);
    public List<CartModel>  getAllCartDetail();
    public CartModel getCartDetailById(Long id);
    public String delete(Long id);
    public CartModel updateCartDetailById(CartDTO cartDTO, Long id);
//    public CartModel updateQuantity(CartDTO cartDTO, Long id,int quantity);
public CartModel updateQuantity(CartDTO cartDTO, Long id,int quantity);
}
