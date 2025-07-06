package com.aluracursos.literalura.repositorio;

import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    @Query("SELECT l FROM Libro l ORDER BY l.idiomas ASC")
    List<Libro> ordenarPorIdioma();
    List<Libro> findByIdiomas(String idioma);
}
