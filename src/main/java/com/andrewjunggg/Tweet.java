package com.andrewjunggg;

public class Tweet {
    private String message;

    Tweet(String messageString) {
        this.message = messageString;
    }

    // getter

    public String getMessage() {
        return message;
    }

    // setter

    public void setMessage(String messageString) {
        this.message = messageString;
    }
}
