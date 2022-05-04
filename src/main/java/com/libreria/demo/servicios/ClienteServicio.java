
package com.libreria.demo.servicios;

import java.util.List;
import java.util.Optional;
import com.libreria.demo.entidades.Cliente;
import com.libreria.demo.errores.ErrorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libreria.demo.repositorios.ClienteRepositorio;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ClienteServicio {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    @Autowired
    private FotoServicio fotoServicio;
    
   //@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Transactional(propagation = Propagation.NESTED)
    public void registrar(MultipartFile archivo, Long documento, String nombre, String apellido, String telefono, String mail, String clave) throws ErrorServicio{
        
        if (documento == null) {
            throw new ErrorServicio("El documento no puede ser nulo");
        }
        validar(nombre, apellido, telefono, mail, clave);
        
        Cliente cliente = new Cliente();
        cliente.setDocumento(documento);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setMail(mail);
        cliente.setClave(clave);
        cliente.setAlta(true);
        cliente.setFoto(fotoServicio.Guardar(archivo));
        
        clienteRepositorio.save(cliente);
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void modificar (MultipartFile archivo, String id, String nombre, String apellido, String telefono, String mail, String clave) throws ErrorServicio{
        
        validar(nombre, apellido, telefono, mail, clave);
        
        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);
            cliente.setMail(mail);
            cliente.setClave(clave);
            String idFoto = null;
            if (cliente.getFoto()!=null) {
                idFoto = cliente.getFoto().getId();
            }
            cliente.setFoto(fotoServicio.actualizar(idFoto, archivo));
            clienteRepositorio.save(cliente);
        }else{
            throw new ErrorServicio("No se encontró el id");
        }
        
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void alta(String id) throws ErrorServicio{
        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setAlta(true);
            clienteRepositorio.save(cliente);
        }else{
            throw new ErrorServicio("No se encontró el id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void baja(String id) throws ErrorServicio{
        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setAlta(false);
            clienteRepositorio.save(cliente);
        }else{
            throw new ErrorServicio("No se encontró el id");
        }
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void borrarPorId (String id) throws ErrorServicio{
        Optional<Cliente> optional = clienteRepositorio.findById(id);
        if (optional.isPresent()) {
            clienteRepositorio.delete(optional.get());
        }else{
            throw new ErrorServicio("No se encontró el id");
        }
    } 
    
    @Transactional(readOnly = true)
    public List<Cliente> mostrarTodos(){
        return clienteRepositorio.findAll();
    }
    
    public void validar(String nombre, String apellido, String telefono, String mail, String clave) throws ErrorServicio{

        if (nombre == null) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }
        if (apellido == null) {
            throw new ErrorServicio("El apellido no puede ser nulo");
        }
        if (telefono == null) {
            throw new ErrorServicio("El telefono no puede ser nulo");
        }
        if (mail == null) {
            throw new ErrorServicio("El mail no puede ser nulo");
        }
        if (clave == null || clave.length()<6) {
            throw new ErrorServicio("La clave no puede ser menor a 6 caracteres");
        }
    }
    
}
