package com.anime.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService  {

    ResponseEntity<String> registro(Map<String, String> requestMap);
}
