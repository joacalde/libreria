package com.libreria.demo.servicios;

import java.util.Optional;
import com.libreria.demo.entidades.Foto;
import com.libreria.demo.errores.ErrorServicio;
import com.libreria.demo.repositorios.FotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {

    @Autowired
    private FotoRepositorio fotoRepositorio;

    @Transactional(propagation = Propagation.NESTED)
    public Foto Guardar(MultipartFile archivo) {
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
                return fotoRepositorio.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } 
        return null;
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public Foto actualizar(String id, MultipartFile archivo) throws ErrorServicio{
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                if (id!=null) {
                    Optional<Foto> respuesta = fotoRepositorio.findById(id);
                    if (respuesta.isPresent()) {
                        foto = respuesta.get();
                    }
                }
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
                return fotoRepositorio.save(foto);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } 
        return null;
    }

}
