package com.bridgelabz.bookstore.exception;

import com.bridgelabz.bookstore.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BookExceptionHandler {
    private static final String message= "Exception while processing REST Request";
    @ExceptionHandler(BookException.class)
    public ResponseEntity<ResponseDTO> handleBookException(BookException exception){
        ResponseDTO responseDTO = new ResponseDTO(message,exception.getMessage(),null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
