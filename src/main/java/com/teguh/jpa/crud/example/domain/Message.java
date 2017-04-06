package com.teguh.jpa.crud.example.domain;

import java.io.Serializable;

/**
 * Message binding helper yang dipergunakan untuk menampilkan flash message pada halaman html.
 */
public final class Message implements Serializable {
    private static final long serialVersionUID = 7503750389106359368L;
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";
    private String message;
    private String type;

    public Message(String type, String message) {
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

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
