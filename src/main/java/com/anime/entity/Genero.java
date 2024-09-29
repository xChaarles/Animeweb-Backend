package com.anime.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "genero")
public class Genero{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer gid;

    @Column
    private String gnombre;

}
