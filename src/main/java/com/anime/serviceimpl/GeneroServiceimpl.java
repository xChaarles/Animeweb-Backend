package com.anime.serviceimpl;

import com.anime.dao.GeneroDao;
import com.anime.entity.Genero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GeneroServiceimpl {

    @Autowired
    private GeneroDao generoDao;

    public List<Genero> findAll() {
        return generoDao.findAll();
    }

    public Genero findById(Integer id) {
        return generoDao.findById(id).orElse(null);
    }

    public Genero save(Genero genero) {
        return generoDao.save(genero);
    }

    public void delete(Integer id) {
        generoDao.deleteById(id);
    }

}
