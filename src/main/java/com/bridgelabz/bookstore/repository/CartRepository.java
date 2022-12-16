package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartModel ,Long> {

}
