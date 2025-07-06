package com.aluracursos.literalura.repositorio;

import com.aluracursos.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreContainsIgnoreCase(String nombre);
    @Query("SELECT a FROM Autor a WHERE a.nacimiento <= :anno AND (a.fallecimiento IS NULL OR a.fallecimiento >= :anno)")
    List<Autor> AutorVivoEnElAnno(int anno);
}
