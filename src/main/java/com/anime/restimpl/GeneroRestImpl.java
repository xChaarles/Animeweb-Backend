package com.anime.restimpl;

import com.anime.entity.Genero;
import com.anime.serviceimpl.GeneroServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class GeneroRestImpl {

    @Autowired
    private GeneroServiceimpl generoServiceimpl;

    @GetMapping("public/generos")
    public List<Genero> getAll() {
        return generoServiceimpl.findAll();
    }

    @GetMapping("public/generos/{id}")
    public ResponseEntity<Genero> getById(@PathVariable Integer id) {
        Genero genero = generoServiceimpl.findById(id);
        return genero != null ? ResponseEntity.ok(genero) : ResponseEntity.notFound().build();
    }

    @PostMapping("/admin/add-genero")
    public ResponseEntity<Genero> create(@RequestBody Genero categoria) {
        Genero nuevaCategoria = generoServiceimpl.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    @PutMapping("/admin/update-genero/{id}")
    public ResponseEntity<Genero> update(@PathVariable Integer id, @RequestBody Genero genero) {
        genero.setGid(id);
        Genero generoActualizado = generoServiceimpl.save(genero);
        return ResponseEntity.ok(generoActualizado);
    }

    @DeleteMapping("/admin/delete-genero/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        generoServiceimpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
