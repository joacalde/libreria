
package com.libreria.demo.repositorios;

import java.util.List;
import com.libreria.demo.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>  {
    
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public List<Libro> buscarLibroPorNombre(@Param("titulo") String nombre);
}
