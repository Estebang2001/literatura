package com.aluracursos.literatura.repository;

import com.aluracursos.literatura.model.Autor;
import com.aluracursos.literatura.model.DatosLibro;
import com.aluracursos.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT a FROM Autor a WHERE a.añoNacimiento <= :año AND (a.añoFallecimiento IS NULL OR a.añoFallecimiento > :año)")
    List<Autor> findAutoresVivosEnAno(@Param("año") Integer año);
}
