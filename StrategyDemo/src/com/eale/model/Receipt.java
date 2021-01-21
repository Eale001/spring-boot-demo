package com.eale.model;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description
 * @Version 1.0
 **/
public class Receipt {

    private String message;

    private String type;

    public Receipt(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
