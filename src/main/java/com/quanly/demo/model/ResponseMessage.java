package com.quanly.demo.model;

import lombok.Data;

@Data
public class ResponseMessage {
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }
}
