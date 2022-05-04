package com.libreria.demo.servicios;

import java.util.List;
import java.util.Optional;
import com.libreria.demo.entidades.Autor;
import com.libreria.demo.entidades.Editorial;
import com.libreria.demo.entidades.Libro;
import com.libreria.demo.errores.ErrorServicio;
import com.libreria.demo.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {
    
    @Autowired 
    private LibroRepositorio libroRepositorio;
    
    @Transactional(propagation = Propagation.NESTED)
    public void registrar(Long isbn, String titulo, int anio, int ejemplares, int ejemplaresprestados, Autor autor, Editorial editorial) throws ErrorServicio{
        validar(isbn, titulo, autor, editorial);
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setAlta(true);
        libro.setAnio(anio);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresprestados(ejemplaresprestados);
        libro.setEjemplaresrestantes(ejemplares-ejemplaresprestados);
        libro.setTitulo(titulo);
        libroRepositorio.save(libro);
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, Long isbn, String titulo, int anio, int ejemplares, int emeplaresprestados, Autor autor, Editorial editorial) throws ErrorServicio{
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            validar(isbn, titulo, autor, editorial);
            Libro libro = new Libro();
            libro.setAnio(anio);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresprestados(emeplaresprestados);
            libro.setEjemplaresrestantes(ejemplares-emeplaresprestados);
            libro.setTitulo(titulo);
            libroRepositorio.save(libro);
        }else{
            throw new ErrorServicio("No se encontro el id");
        }
        
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void alta(String id)throws ErrorServicio{
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(true);
            libroRepositorio.save(libro);
        }else{
            throw new ErrorServicio("No se ha encontrado el id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void baja(String id)throws ErrorServicio{
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setAlta(false);
            libroRepositorio.save(libro);
        }else{
            throw new ErrorServicio("No se ha encontrado el id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorId (String id) throws ErrorServicio{
        Optional<Libro> optional = libroRepositorio.findById(id);
        if (optional.isPresent()) {
            libroRepositorio.delete(optional.get());
        }else{
            throw new ErrorServicio("No se encontró el id");
        }
    } 
    
    @Transactional(readOnly = true)
    public List<Libro> mostrarTodos(){
        return libroRepositorio.findAll();
    }

    private void validar(Long isbn, String titulo, Autor autor, Editorial editorial) throws ErrorServicio {
        if (isbn == null) {
            throw new ErrorServicio("isbn no puede ser nulo");
        }
        if (titulo == null) {
            throw new ErrorServicio("titulo no puede ser nulo");
        }
        if (autor == null) {
            throw new ErrorServicio("autor no puede ser nulo");
        }
        if (editorial == null) {
            throw new ErrorServicio("editorial no puede ser nula");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public Libro buscarPorId (String id) throws ErrorServicio{
        Optional<Libro> optional = libroRepositorio.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }else{
            throw new ErrorServicio("No se encontró el id");
        }
    } 
    
    @Transactional(propagation = Propagation.NESTED)
    public void agregarejemplares(String id) throws ErrorServicio{
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setEjemplares(libro.getEjemplares()+1);
            libroRepositorio.save(libro);
        }else{
            throw new ErrorServicio("No se encontro el id");
        }
        
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void sacarejemplares(String id) throws ErrorServicio{
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setEjemplares(libro.getEjemplares()-1);
            libroRepositorio.save(libro);
        }else{
            throw new ErrorServicio("No se encontro el id");
        }
        
    }
}
