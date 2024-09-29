package com.anime.serviceimpl;

import com.anime.dao.AnimeDao;
import com.anime.dao.GeneroDao;
import com.anime.dto.AnimeRes;
import com.anime.entity.Anime;
import com.anime.entity.Genero;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimeServiceImpl {
    @Autowired
    private AnimeDao animeDao;

    @Autowired
    private GeneroDao generoDao;

    public List<AnimeRes> ListAnime() {
        List<Anime> animes = animeDao.findAll();
        return animes.stream()
                .map(anime -> new AnimeRes(
                        anime.getAid(),
                        anime.getAnombre(),
                        anime.getAdescripcion(),
                        anime.getAportadaUrl(),
                        anime.getFechaEmision(),
                        anime.getGenero().getGnombre()
                ))
                .collect(Collectors.toList());
    }

    public Anime findById(Integer id) {
        return animeDao.findById(id).orElse(null);
    }

    public Anime CreateAnime (AnimeRes animeRes){
        Anime anime = new Anime();

        anime.setAid(animeRes.getAid());
        anime.setAnombre(animeRes.getAnombre());
        anime.setAdescripcion(animeRes.getAdescripcion());
        anime.setAportadaUrl(animeRes.getAportadaUrl());
        anime.setFechaEmision(animeRes.getFechaEmision());

        Optional<Genero> generoOpt = generoDao.findByGnombre(animeRes.getGenero());
        if (generoOpt.isPresent()){
            anime.setGenero(generoOpt.get());
        }else {
            throw new EntityNotFoundException("Genero no encontrado: " + animeRes.getGenero());
        }
        return animeDao.save(anime);
    }

    public AnimeRes updateAnime(Integer aid, AnimeRes updateRest) {
        Anime animes = animeDao.findById(aid)
                .orElseThrow(() -> new EntityNotFoundException("Anime no encontrado con id: " + aid));

        animes.setAnombre(updateRest.getAnombre());
        animes.setAdescripcion(updateRest.getAdescripcion());
        animes.setAportadaUrl(updateRest.getAportadaUrl());
        animes.setFechaEmision(updateRest.getFechaEmision());

        Optional<Genero> generoOpt = generoDao.findByGnombre(updateRest.getGenero());
        if (generoOpt.isPresent()){
            animes.setGenero(generoOpt.get());
        }else {
            throw new EntityNotFoundException("Genero no encontrado: " + updateRest.getGenero());
        }

        Anime updateAnime = animeDao.save(animes);

        return new AnimeRes(
                updateAnime.getAid(),
                updateAnime.getAnombre(),
                updateAnime.getAdescripcion(),
                updateAnime.getAportadaUrl(),
                updateAnime.getFechaEmision(),
                updateAnime.getGenero().getGnombre()
        );
    }

    public void delete(Integer id) {
        animeDao.deleteById(id);
    }
}
