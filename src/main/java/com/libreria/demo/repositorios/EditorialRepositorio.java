
package com.libreria.demo.repositorios;

import java.util.List;
import com.libreria.demo.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {
    
    @Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
    public Editorial buscarEditorialPorNombre (@Param("nombre")String nombre);
    
    @Query("SELECT a FROM Editorial a WHERE a.alta = true")
    public List<Editorial> mostrarAlta();
    
    @Query("SELECT a FROM Editorial a WHERE a.alta = false")
    public List<Editorial> mostrarBaja();
}
