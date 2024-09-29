package com.anime.dao;

import com.anime.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GeneroDao extends JpaRepository<Genero, Integer> {
    Optional<Genero> findByGnombre( String gnombre);
}
