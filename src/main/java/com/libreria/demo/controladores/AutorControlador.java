package com.libreria.demo.controladores;

import com.libreria.demo.entidades.Autor;
import com.libreria.demo.errores.ErrorServicio;
import com.libreria.demo.servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class AutorControlador {
    
    @Autowired
    private AutorServicio autorServicio;
    
    
    @GetMapping("/autor")
    public String autor(ModelMap model) {
        List<Autor> autor = autorServicio.mostrarAlta();
        model.put("autor", autor);
        return "autor.html";
    }
    
    @PostMapping("/autorregistrar")
    public String registrarAutor(ModelMap model, @RequestParam String nombreAutor) throws ErrorServicio{
        try {
            
            autorServicio.registrar(nombreAutor);
            List<Autor> autor = autorServicio.mostrarTodos();
            model.put("autor", autor);
            return "redirect:/autor#tablita";

        } catch (ErrorServicio e) {
            List<Autor> autor = autorServicio.mostrarTodos();
            model.put("autor", autor);
            model.put("mensaje", e.getMessage());
            return "/autor";
        }   
    }
    
    @GetMapping("/eliminarautor/{id}")
    public String eliminarAutor(ModelMap model, @PathVariable("id") String id) throws ErrorServicio{
        try {
           autorServicio.borrarPorId(id);
           List<Autor> autor = autorServicio.mostrarTodos();
           model.put("autor", autor);
           return "redirect:/autor#tablita";
        } catch (ErrorServicio e) {
            List<Autor> autor = autorServicio.mostrarTodos();
            model.put("autor", autor);
            return "redirect:/autor#tablita"; 
        }
    }
    
    
    @GetMapping("/editarautor/{id}")
    public String editarAutor(ModelMap model, @PathVariable("id") String id) throws ErrorServicio{
        autorServicio.bajaeditar(id);
        List<Autor> autor = autorServicio.mostrarTodos();
        model.put("autor", autor);
        return "redirect:/vertodoshtml#tablita";
    }
    
    @GetMapping("/editarautorcancel/{id}")
    public String editarAutorCancel(ModelMap model, @PathVariable("id") String id) throws ErrorServicio{
        autorServicio.altaeditar(id);
        List<Autor> autor = autorServicio.mostrarTodos();
        model.put("autor", autor);
        return "redirect:/vertodoshtml#tablita";
    }
    
    @GetMapping("/editarautor2")
    public String editarAutor2(ModelMap model, @RequestParam String ideditarautor, @RequestParam String nombreeditarautor) throws ErrorServicio{
        try {
           autorServicio.modificar(ideditarautor, nombreeditarautor);
           List<Autor> autor = autorServicio.mostrarTodos();
           autorServicio.altaeditar(ideditarautor);
           model.put("autor", autor);
           return "redirect:/vertodoshtml#tablita"; 
        } catch (ErrorServicio e) {
            List<Autor> autor = autorServicio.mostrarTodos();
            model.put("autor", autor);
            model.put("mensajeeditar", e.getMessage());
            model.put("ideditar", ideditarautor);
            return "/autor";
        }
    }
    
    @GetMapping("/baja/{id}")
    public String baja(ModelMap model, @PathVariable("id") String id){
        try {
           autorServicio.baja(id);
           List<Autor> autor = autorServicio.mostrarTodos();
           model.put("autor", autor);
           return "redirect:/veraltahtml#tablita"; 
        } catch (ErrorServicio e) {
            List<Autor> autor = autorServicio.mostrarTodos();
            model.put("autor", autor);
            return "/autor";
        }
    }
    
    @GetMapping("/alta/{id}")
    public String alta(ModelMap model, @PathVariable("id") String id){
        try {
           autorServicio.alta(id);
           List<Autor> autor = autorServicio.mostrarTodos();
           model.put("autor", autor);
           return "redirect:/verbajahtml#tablita"; 
        } catch (ErrorServicio e) {
            List<Autor> autor = autorServicio.mostrarTodos();
            model.put("autor", autor);
            return "/autor";
        }
    }
    
    @GetMapping("/verbaja")
    public String verbaja(ModelMap model) {
        return "redirect:/verbajahtml#tablita";
    }
    
    @GetMapping("/verbajahtml")
    public String verbajahtml(ModelMap model) {
        List<Autor> autor = autorServicio.mostrarBaja();
        model.put("autor", autor);
        return "autor.html";
    }
    
    @GetMapping("/veralta")
    public String veralta(ModelMap model) {
        return "redirect:/veraltahtml#tablita";
    }
    
    @GetMapping("/veraltahtml")
    public String veraltahtml(ModelMap model) {
        List<Autor> autor = autorServicio.mostrarAlta();
        model.put("autor", autor);
        return "autor.html";
    }
    
    @GetMapping("/vertodos")
    public String vertodos() {
        return "redirect:/vertodoshtml#tablita";
    }
    
    @GetMapping("/vertodoshtml")
    public String vertodoshtml(ModelMap model) {
        List<Autor> autor = autorServicio.mostrarTodos();
        model.put("autor", autor);
        return "autor.html";
    }
}
