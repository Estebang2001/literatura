package com.aluracursos.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
     @JsonAlias("name")String autor,
     @JsonAlias("birth_year")Integer añoNacimiento,
     @JsonAlias("death_year")Integer añoFallecimiento

) {}