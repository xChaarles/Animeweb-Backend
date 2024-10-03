package com.anime.restimpl;

import com.anime.dto.AnimeRes;
import com.anime.entity.Anime;
import com.anime.serviceimpl.AnimeServiceImpl;
import com.anime.serviceimpl.GeneroServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AnimeRestImpl {

    @Autowired
    private AnimeServiceImpl animeServiceImpl;

    @Autowired
    private GeneroServiceimpl generoServiceimpl;

    @GetMapping("/public/anime")
    public List<AnimeRes> getAll() {
        return animeServiceImpl.ListAnime();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/admin/add-anime")
    public Anime create(@RequestBody AnimeRes animeRes) {
        return animeServiceImpl.CreateAnime(animeRes);
    }

    @GetMapping("public/anime/{id}")
    public ResponseEntity<Anime> getById(@PathVariable Integer id) {
        Anime anime = animeServiceImpl.findById(id);
        return anime != null ? ResponseEntity.ok(anime) : ResponseEntity.notFound().build();
    }

    @PutMapping("/admin/update-anime/{id}")
    public ResponseEntity<AnimeRes> update(@PathVariable Integer id, @RequestBody AnimeRes updateRest) {
        AnimeRes updateAnime = animeServiceImpl.updateAnime(id, updateRest);
        return ResponseEntity.ok(updateAnime);
    }

    @DeleteMapping("/admin/delete-anime/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        animeServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
