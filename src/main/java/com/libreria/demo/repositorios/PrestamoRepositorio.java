
package com.libreria.demo.repositorios;

import java.util.List;
import com.libreria.demo.entidades.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, String>{
    
    @Query("SELECT p FROM Prestamo p WHERE p.cliente.nombre = :nombre")
    public List<Prestamo> buscarPrestamoPorCliente (@Param("nombre") String nombre);
    
}
