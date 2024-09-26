package com.anime.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AnimeUtils {

    private AnimeUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"messag\":\""+responseMessage+"\"}", httpStatus);
    }
}
