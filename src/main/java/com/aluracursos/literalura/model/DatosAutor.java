package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer nacimiento,
        @JsonAlias("death_year") Integer fallecimiento
) {
}
