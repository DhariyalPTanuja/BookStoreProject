package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public  class ResponseBookDTO {

    private String msg;
    private Object data;

    public ResponseBookDTO(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }
}
