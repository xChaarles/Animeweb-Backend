package com.anime.dto;

import com.anime.entity.Genero;
import com.anime.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneroRes {

    private int statusCode;
    private String error;
    private String message;
    private String gnombre;
    private Genero genero;
    private List<Genero> generoList;
}
