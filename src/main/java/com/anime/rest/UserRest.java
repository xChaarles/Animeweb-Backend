package com.anime.rest;

import com.anime.dto.ReqRes;
import com.anime.entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping
public interface UserRest {

    //metodo de interface publica
    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg);

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes reg);

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes reg);

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers();

    @GetMapping("/admin/get-users/{id}")
    public ResponseEntity<ReqRes> getUserById(@PathVariable Integer id);

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody Users reqRes);

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile();

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUser(@PathVariable Integer userId);
}
