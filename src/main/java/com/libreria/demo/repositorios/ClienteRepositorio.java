package com.libreria.demo.repositorios;

import java.util.List;
import com.libreria.demo.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String>{
    
    @Query("SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    public List<Cliente> buscarClientePorNombre (@Param("nombre") String nombre);
}
