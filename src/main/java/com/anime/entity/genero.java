package com.anime.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table (name = "genero")
public class genero {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long gid;

    @Column
    private String gnombre;


}
