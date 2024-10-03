package com.anime.dto;

import java.sql.Date;
import java.time.LocalDate;


public class AnimeRes {

    private Integer aid;
    private String anombre;
    private String adescripcion;
    private String aportadaUrl;
    private LocalDate fechaEmision;
    private String genero;

    public AnimeRes(Integer aid, String anombre, String adescripcion, String aportadaUrl, LocalDate fechaEmision, String genero) {
        this.aid = aid;
        this.anombre = anombre;
        this.adescripcion = adescripcion;
        this.aportadaUrl = aportadaUrl;
        this.fechaEmision = fechaEmision;
        this.genero = genero;
    }

    public AnimeRes(){
    }

    public String getAdescripcion() {
        return adescripcion;
    }

    public void setAdescripcion(String adescripcion) {
        this.adescripcion = adescripcion;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAnombre() {
        return anombre;
    }

    public void setAnombre(String anombre) {
        this.anombre = anombre;
    }

    public String getAportadaUrl() {
        return aportadaUrl;
    }

    public void setAportadaUrl(String aportadaUrl) {
        this.aportadaUrl = aportadaUrl;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
