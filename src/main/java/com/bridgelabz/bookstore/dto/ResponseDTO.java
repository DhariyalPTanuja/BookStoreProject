package com.bridgelabz.bookstore.dto;

import lombok.Data;

public @Data class ResponseDTO {
    private String msg;
    private Object data;
    private String token;

    public ResponseDTO(String msg, Object data, String token) {
        this.msg = msg;
        this.data = data;
        this.token = token;
    }
}
