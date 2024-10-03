package com.anime.entity;

import com.anime.constants.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "anime")
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;

    @Column
    private String anombre;

    @Column
    private String adescripcion;

    @Column
    private String aportadaUrl;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column
    private LocalDate fechaEmision;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;


}
