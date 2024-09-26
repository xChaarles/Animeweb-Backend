package com.anime.dao;

import com.anime.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<Users, Integer> {

    //validador para que el email no se este repitiendo
    Users findByEmailId(@Param("uemail") String uemail);
}
