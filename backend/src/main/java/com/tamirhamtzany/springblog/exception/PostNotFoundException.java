package com.tamirhamtzany.springblog.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String s) {
        super(s);
    }
}
