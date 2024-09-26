package com.anime.restimpl;

import com.anime.dto.ReqRes;
import com.anime.entity.Users;
import com.anime.rest.UserRest;
import com.anime.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Override
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg) {
        return ResponseEntity.ok(userServiceImpl.register(reg));
    }

    @Override
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes reg) {
        return ResponseEntity.ok(userServiceImpl.login(reg));
    }

    @Override
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes reg){
        return ResponseEntity.ok(userServiceImpl.refreshToken(reg));
    }

    @Override
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(userServiceImpl.getAllUsers());
    }

    @Override
    public ResponseEntity<ReqRes> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userServiceImpl.getUsersById(id));
    }

    @Override
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody Users reqRes){
        return ResponseEntity.ok(userServiceImpl.updateUser(userId, reqRes));
    }

    @Override
    public ResponseEntity<ReqRes> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = userServiceImpl.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @Override
    public ResponseEntity<ReqRes> deleteUser(@PathVariable Integer userId){
        return ResponseEntity.ok(userServiceImpl.deleteUser(userId));
    }
}
