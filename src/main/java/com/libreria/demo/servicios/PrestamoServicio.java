package com.libreria.demo.servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.libreria.demo.entidades.Cliente;
import com.libreria.demo.entidades.Libro;
import com.libreria.demo.entidades.Prestamo;
import com.libreria.demo.errores.ErrorServicio;
import com.libreria.demo.repositorios.PrestamoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrestamoServicio {
    
    @Autowired 
    private PrestamoRepositorio prestamoRepositorio;
    
    
    @Transactional(propagation = Propagation.NESTED)
    public void registrar(Date fechaPrestamo, Date fechaDevolucion, Libro libro, Cliente cliente) throws ErrorServicio{
        validar(fechaPrestamo, fechaDevolucion, libro, cliente);
        Prestamo prestamo = new Prestamo();
        prestamo.setAlta(true);
        prestamo.setCliente(cliente);
        prestamo.setFechaDevolucion(fechaDevolucion);
        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setLibro(libro);
        prestamoRepositorio.save(prestamo);
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, Date fechaPrestamo, Date fechaDevolucion, Libro libro, Cliente cliente) throws ErrorServicio{
        Optional<Prestamo> respuesta = prestamoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            validar(fechaPrestamo, fechaDevolucion, libro, cliente);
            Prestamo prestamo = new Prestamo();
            prestamo.setAlta(true);
            prestamo.setCliente(cliente);
            prestamo.setFechaDevolucion(fechaDevolucion);
            prestamo.setFechaPrestamo(fechaPrestamo);
            prestamo.setLibro(libro);
            prestamoRepositorio.save(prestamo);
        }else{
            throw new ErrorServicio("No se encontro el id");
        }
        
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void alta(String id)throws ErrorServicio{
        Optional<Prestamo> respuesta = prestamoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Prestamo prestamo = respuesta.get();
            prestamo.setAlta(true);
            prestamoRepositorio.save(prestamo);
        }else{
            throw new ErrorServicio("No se ha encontrado el id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void baja(String id)throws ErrorServicio{
        Optional<Prestamo> respuesta = prestamoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Prestamo prestamo = respuesta.get();
            prestamo.setAlta(false);
            prestamoRepositorio.save(prestamo);
        }else{
            throw new ErrorServicio("No se ha encontrado el id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorId (String id) throws ErrorServicio{
        Optional<Prestamo> optional = prestamoRepositorio.findById(id);
        if (optional.isPresent()) {
            prestamoRepositorio.delete(optional.get());
        }else{
            throw new ErrorServicio("No se encontró el id");
        }
    } 
    
    @Transactional(readOnly = true)
    public List<Prestamo> mostrarTodos(){
        return prestamoRepositorio.findAll();
    }

    private void validar(Date fechaPrestamo, Date fechaDevolucion, Libro libro, Cliente cliente) throws ErrorServicio {
        if (fechaPrestamo == null) {
            throw new ErrorServicio("La fecha del prestamo no puede ser nula");
        }
        if (fechaDevolucion == null) {
            throw new ErrorServicio("La fecha de devolucion no puede ser nula");
        }
        if (libro == null) {
            throw new ErrorServicio("El libro no puede ser nulo");
        }
        if (cliente == null) {
            throw new ErrorServicio("El cliente no puede ser nulo");
        }
    }
}
