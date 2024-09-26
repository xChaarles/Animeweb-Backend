package com.anime.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path = "/user")
public interface UserRest {

    //metodo de interface publica
    @PostMapping(name = "registro")
    public ResponseEntity<String> registro(@RequestBody(required = true)Map<String, String> requestMap);
}
