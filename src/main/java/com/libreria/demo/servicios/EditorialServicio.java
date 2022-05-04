package com.libreria.demo.servicios;

import java.util.List;
import java.util.Optional;
import com.libreria.demo.entidades.Editorial;
import com.libreria.demo.errores.ErrorServicio;
import com.libreria.demo.repositorios.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {
    
    @Autowired 
    private EditorialRepositorio editorialRepositorio;
    
    
    @Transactional(propagation = Propagation.NESTED)
    public void registrar(String nombre) throws ErrorServicio{
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);
        editorial.setEditar(true);
        editorialRepositorio.save(editorial);
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, String nombre) throws ErrorServicio{
        validar(nombre);
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
        }else{
            throw new ErrorServicio("No se ha encontrado el id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void baja(String id)throws ErrorServicio{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(false);
            editorialRepositorio.save(editorial);
        }else{
            throw new ErrorServicio("No se ha encontrado el id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void alta(String id)throws ErrorServicio{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(true);
            editorialRepositorio.save(editorial);
        }else{
            throw new ErrorServicio("No se ha encontrado el id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorId (String id) throws ErrorServicio{
        Optional<Editorial> optional = editorialRepositorio.findById(id);
        if (optional.isPresent()) {
            editorialRepositorio.delete(optional.get());
        }else{
            throw new ErrorServicio("No se encontró el id");
        }
    } 
    
    @Transactional(readOnly = true)
    public List<Editorial> mostrarTodos(){
        return editorialRepositorio.findAll();
    }
    
    public void validar(String nombre) throws ErrorServicio{
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo"); 
        }
        if (editorialRepositorio.buscarEditorialPorNombre(nombre)!=null) {
            throw new ErrorServicio("El nombre ya existe");
        }
    }
    
    @Transactional(readOnly = true)
    public Editorial buscarPorNombre(String nombre){
        Editorial autor = editorialRepositorio.buscarEditorialPorNombre(nombre);
        if (autor!=null) {
           return editorialRepositorio.buscarEditorialPorNombre(nombre); 
        }else{
            return null;
        }
            
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void bajaeditar(String id)throws ErrorServicio{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial autor = respuesta.get();
            autor.setEditar(false);
            editorialRepositorio.save(autor);
        }else{
            throw new ErrorServicio("No se ha encontrado el id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void altaeditar(String id)throws ErrorServicio{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial autor = respuesta.get();
            autor.setEditar(true);
            editorialRepositorio.save(autor);
        }else{
            throw new ErrorServicio("No se ha encontrado el id");
        }
    }

    @Transactional(readOnly = true)
    public List<Editorial> mostrarAlta(){
        return editorialRepositorio.mostrarAlta();
    }
    
    @Transactional(readOnly = true)
    public List<Editorial> mostrarBaja(){
        return editorialRepositorio.mostrarBaja();
    }

}
