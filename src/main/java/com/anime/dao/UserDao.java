package com.anime.dao;

import com.anime.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<Users, Integer> {

    Optional<Users> findByUemail(String uemail);
}
