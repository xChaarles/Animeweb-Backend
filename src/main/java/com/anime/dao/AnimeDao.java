package com.anime.dao;

import com.anime.entity.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeDao extends JpaRepository<Anime, Integer> {
}
