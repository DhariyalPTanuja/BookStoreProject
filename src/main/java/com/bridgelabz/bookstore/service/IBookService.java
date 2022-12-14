package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.BookModel;

import java.util.List;

public interface IBookService {

    public BookModel insertBook(BookDTO bookDTO);
    public List<BookModel> getAllBookRecord();
    public BookModel getBookRecordById(Long id);
    public String deleteBook(Long id);
    public BookModel updateBookRecordById(BookDTO bookDTO,Long id);
    public BookModel searchByBookName(String bookName);
    public BookModel updateBookQuantity(BookDTO bookDTO,Long Id,int quantity);

    public List<BookModel> sortingAsce();
    public List<BookModel> sortingDesc();

}
