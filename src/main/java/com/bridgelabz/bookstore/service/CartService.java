package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.exception.BookStoreException;

import com.bridgelabz.bookstore.model.BookId;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.model.CartModel;
import com.bridgelabz.bookstore.model.UserModel;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CartService implements ICartService {
    List<CartModel> cartList = new ArrayList<>();
    @Autowired
    CartRepository repoCart;
    @Autowired
    UserRepository repoUser;
    @Autowired
    BookRepository repoBook;
    //Apply Logic for  inserting cart Detail
    @Override
    public CartModel insert(CartDTO cartDTO) {
        Optional<BookModel> checkBookId = repoBook.findById(cartDTO.bookID.getBookID());
        Optional<UserModel> checkUserId = repoUser.findById(cartDTO.userID.getUserID());
        if (checkUserId.isPresent() && checkBookId.isPresent()) {
            CartModel cartModel = new CartModel(cartDTO);
            return repoCart.save(cartModel);
        } else
            throw new BookStoreException("UserId or Book Id is not found");
    }
    //Apply Logic for getting all cart Detail
    @Override
    public List<CartModel> getAllCartDetail() {
        cartList = repoCart.findAll();
        return cartList;
    }
    //Apply Logic for getting particular cart record which will be found by id
    @Override
    public CartModel getCartDetailById(Long id) {
        Optional<CartModel> checkCart = repoCart.findById(id);
        if(checkCart.isPresent())
            return checkCart.get();
        else
            throw new BookStoreException("id is not found");
    }
    //Apply Logic for deleting particular cart detail which will be deleted by id
    @Override
    public String delete(Long id) {
        Optional<CartModel> checkID =repoCart.findById(id);
        if (checkID.isPresent())
            repoCart.deleteById(id);
        else
            return "id is not found";
        return "data delete";
    }
    //Apply Logic for updating particular cart detail by id
    @Override
    public CartModel updateCartDetailById(CartDTO cartDTO, Long id) {
        Optional<CartModel> checkId = repoCart.findById(id);
        Optional<BookModel> checkBookId = repoBook.findById(cartDTO.bookID.getBookID());
        Optional<UserModel> checkUserId = repoUser.findById(cartDTO.userID.getUserID());
        if (checkId.isPresent()) {
            if (checkUserId.isPresent() && checkBookId.isPresent()) {
                CartModel cartModel = new CartModel(cartDTO);
                cartModel.setCartID(id);
                CartModel updateCartModel = repoCart.save(cartModel);
                return updateCartModel;
            } else
                throw new BookStoreException("Book ID or User Id is not Found!! update failed");
        }else
            throw new BookStoreException("Id is not Found!! update failed");
    }

    //Apply Logic for updating only quantity to particular cart detail by id
    @Override
    public CartModel updateQuantity(CartDTO cartDTO, Long id, int quantity) {
        Optional<CartModel> checkId = repoCart.findById(id);
        Optional<BookModel> checkBookId = repoBook.findById(cartDTO.bookID.getBookID());
        int bookQuantity = checkBookId.get().getQuantity();
        if (checkId.isPresent()) {
            if(quantity < bookQuantity){
                CartModel cartModel = new CartModel(cartDTO);
                cartModel.setCartID(id);
                cartModel.setCartQuantity(quantity);
                cartModel.setBookID(checkId.get().getBookID());
                cartModel.setUserID(checkId.get().getUserID());
                CartModel updateCartModel = repoCart.save(cartModel);
                return updateCartModel;
            }else
                throw new BookStoreException("book quantity " +quantity + " is not available. only " +bookQuantity + "is available");
        } else
            throw new BookStoreException("Id is not Found!! update failed");
    }

}
