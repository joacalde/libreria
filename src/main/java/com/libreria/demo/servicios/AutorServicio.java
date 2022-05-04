
package com.libreria.demo.servicios;

import java.util.List;
import java.util.Optional;
import com.libreria.demo.entidades.Autor;
import com.libreria.demo.errores.ErrorServicio;
import com.libreria.demo.repositorios.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {
    
    @Autowired 
    private AutorRepositorio autorRepositorio;
    
    
    @Transactional(propagation = Propagation.NESTED)
    public void registrar(String nombre) throws ErrorServicio{
        validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true);
        autor.setEditar(true);
        autorRepositorio.save(autor);
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, String nombre) throws ErrorServicio{
        validar(nombre);
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }else{
            throw new ErrorServicio("No se ha encontrado el id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void baja(String id)throws ErrorServicio{
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(false);
            autorRepositorio.save(autor);
        }else{
            throw new ErrorServicio("No se ha encontrado el id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void alta(String id)throws ErrorServicio{
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(true);
            autorRepositorio.save(autor);
        }else{
            throw new ErrorServicio("No se ha encontrado el id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorId (String id) throws ErrorServicio{
        Optional<Autor> optional = autorRepositorio.findById(id);
        if (optional.isPresent()) {
            autorRepositorio.delete(optional.get());
        }else{
            throw new ErrorServicio("No se encontró el id");
        }
    } 
    
    @Transactional(readOnly = true)
    public List<Autor> mostrarTodos(){
        return autorRepositorio.findAll();
    }

    public void validar(String nombre) throws ErrorServicio{
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo"); 
        }
        if (autorRepositorio.buscarAutorPorNombre(nombre)!=null) {
            throw new ErrorServicio("El nombre ya existe");
        }
    }
    
    @Transactional(readOnly = true)
    public Autor buscarPorNombre(String nombre){
        Autor autor = autorRepositorio.buscarAutorPorNombre(nombre);
        if (autor!=null) {
           return autorRepositorio.buscarAutorPorNombre(nombre); 
        }else{
            return null;
        }
            
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void bajaeditar(String id)throws ErrorServicio{
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setEditar(false);
            autorRepositorio.save(autor);
        }else{
            throw new ErrorServicio("No se ha encontrado el id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void altaeditar(String id)throws ErrorServicio{
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setEditar(true);
            autorRepositorio.save(autor);
        }else{
            throw new ErrorServicio("No se ha encontrado el id");
        }
    }

    @Transactional(readOnly = true)
    public List<Autor> mostrarAlta(){
        return autorRepositorio.mostrarAlta();
    }
    
    @Transactional(readOnly = true)
    public List<Autor> mostrarBaja(){
        return autorRepositorio.mostrarBaja();
    }


}
