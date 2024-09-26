package com.anime.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "anime")
public class anime {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long aid;

    @Column(name = "anombre") private String anombre;

    @Column
    private String descripcion;

    @Column
    private String genero;

    @Column
    private String portada;


    @Column
    private String capitulos;

    @Column
    private Date fec_emision;


}
