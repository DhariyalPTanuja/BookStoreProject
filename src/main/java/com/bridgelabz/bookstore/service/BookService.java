package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.exception.BookException;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    List<BookModel> bookList = new ArrayList<>();
    @Autowired
    BookRepository repoBook;


//Apply Logic for inserting Book Record
    @Override
    public BookModel insertBook(BookDTO bookDTO) {
        BookModel bookObj = new BookModel(bookDTO);
        return repoBook.save(bookObj);
    }
    //Apply Logic for getting all book record
    @Override
    public List<BookModel> getAllBookRecord() {
        bookList = repoBook.findAll();
        return bookList;
    }
    //Apply Logic for getting particular book record which will be found by id
    @Override
    public BookModel getBookRecordById(Long id) {
        Optional<BookModel> checkId = repoBook.findById(id);
        if (checkId.isPresent())
            return checkId.get();
        else
            throw new BookException("id is not present");
    }
    //Apply Logic for deleting particular book record which will be delete by id
    @Override
    public String deleteBook(Long id) throws BookException {
        Optional<BookModel> checkID = repoBook.findById(id);
        if (checkID.isPresent())
            repoBook.deleteById(id);
        else
            return "id is not found";
        return "data delete";
    }
    //Apply Logic for updating particular book record by id
    @Override
    public BookModel updateBookRecordById(BookDTO bookDTO, Long id) {
        Optional<BookModel> checkId = repoBook.findById(id);
        if (checkId.isPresent()) {
            BookModel bookModel = new BookModel(bookDTO);
            bookModel.setBookID(id);
            BookModel updateBookModel = repoBook.save(bookModel);
            return updateBookModel;
        } else
            throw new BookException("Id is not Found!! update failed");
    }
    //Apply Logic for searching particular book record which will be found by Book Name
    @Override
    public BookModel searchByBookName(String bookName) {
        BookModel checkBook = repoBook.findRecordByBook(bookName);
        if (checkBook != null)
            return checkBook;
        else
            throw new BookException("Sorry!!! we can not find the book:" + bookName);
    }
    //Apply Logic for updating particular book quantity  by id
    @Override
    public BookModel updateBookQuantity(BookDTO bookDTO,Long id,int quantity) {
        Optional<BookModel> checkBook = repoBook.findById(id);
        if (checkBook.isPresent()) {
            BookModel bookModel = new BookModel(bookDTO);
            bookModel.setBookID(id);
            bookModel.setQuantity(quantity);
            BookModel updateBookModel = repoBook.save(bookModel);
            return updateBookModel;

        }
        else
            throw new BookException("Id is not Found!! update failed");
    }
    //Apply Logic for sorting book record based on price in ascending order
    @Override
    public List <BookModel> sortingAsce() {
        bookList =  repoBook.sortingAsceByPrice();
        return bookList;
    }
    //Apply Logic for sorting book record based on price in descending order
    @Override
    public List<BookModel> sortingDesc() {
        bookList =  repoBook.sortingDescByPrice();
        return bookList;
    }
    }

