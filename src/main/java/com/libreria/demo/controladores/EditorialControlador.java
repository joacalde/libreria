package com.libreria.demo.controladores;

import com.libreria.demo.entidades.Editorial;
import com.libreria.demo.errores.ErrorServicio;
import com.libreria.demo.servicios.EditorialServicio;
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
public class EditorialControlador {
    
    @Autowired
    private EditorialServicio editorialServicio;
    
    

    @GetMapping("/editorial")
    public String editorial(ModelMap model) {
        List<Editorial> editorial = editorialServicio.mostrarAlta();
        model.put("editorial", editorial);
        return "editorial.html";
    }
    
    @PostMapping("/editorialregistrar")
    public String registrarEditorial(ModelMap model, @RequestParam String nombreEditorial) throws ErrorServicio{
        try {
            
            editorialServicio.registrar(nombreEditorial);
            List<Editorial> editorial = editorialServicio.mostrarTodos();
            model.put("editorial", editorial);
            return "redirect:/editorial#tablita";

        } catch (ErrorServicio e) {
            List<Editorial> editorial = editorialServicio.mostrarTodos();
            model.put("editorial", editorial);
            model.put("mensaje", e.getMessage());
            return "/editorial";
        }   
    }
    
    @GetMapping("/eliminareditorial/{id}")
    public String eliminarEditorial(ModelMap model, @PathVariable("id") String id) throws ErrorServicio{
        try {
           editorialServicio.borrarPorId(id);
           List<Editorial> editorial = editorialServicio.mostrarTodos();
           model.put("editorial", editorial);
           return "redirect:/editorial#tablita";
        } catch (ErrorServicio e) {
            List<Editorial> editorial = editorialServicio.mostrarTodos();
            model.put("editorial", editorial);
            return "redirect:/editorial#tablita"; 
        }
    }
    
    
    @GetMapping("/editareditorial/{id}")
    public String editarEditorial(ModelMap model, @PathVariable("id") String id) throws ErrorServicio{
        editorialServicio.bajaeditar(id);
        List<Editorial> editorial = editorialServicio.mostrarTodos();
        model.put("editorial", editorial);
        return "redirect:/vertodoshtmleditorial#tablita";
    }
    
    @GetMapping("/editareditorialcancel/{id}")
    public String editarEditorialCancel(ModelMap model, @PathVariable("id") String id) throws ErrorServicio{
        editorialServicio.altaeditar(id);
        List<Editorial> editorial = editorialServicio.mostrarTodos();
        model.put("editorial", editorial);
        return "redirect:/vertodoshtmleditorial#tablita";
    }
    
    @GetMapping("/editareditorial2")
    public String editarEditorial2(ModelMap model, @RequestParam String ideditarautor, @RequestParam String nombreeditarautor) throws ErrorServicio{
        try {
           editorialServicio.modificar(ideditarautor, nombreeditarautor);
           List<Editorial> editorial = editorialServicio.mostrarTodos();
           editorialServicio.altaeditar(ideditarautor);
           model.put("editorial", editorial);
           return "redirect:/vertodoshtmleditorial#tablita"; 
        } catch (ErrorServicio e) {
            List<Editorial> editorial = editorialServicio.mostrarTodos();
            model.put("editorial", editorial);
            model.put("mensajeeditar", e.getMessage());
            model.put("ideditar", ideditarautor);
            return "/editorial";
        }
    }
    
    @GetMapping("/bajaeditorial/{id}")
    public String bajaeditorial(ModelMap model, @PathVariable("id") String id){
        try {
           editorialServicio.baja(id);
           List<Editorial> editorial = editorialServicio.mostrarTodos();
           model.put("editorial", editorial);
           return "redirect:/veraltahtmleditorial#tablita"; 
        } catch (ErrorServicio e) {
            List<Editorial> editorial = editorialServicio.mostrarTodos();
            model.put("editorial", editorial);
            return "/editorial";
        }
    }
    
    @GetMapping("/altaeditorial/{id}")
    public String altaeditorial(ModelMap model, @PathVariable("id") String id){
        try {
           editorialServicio.alta(id);
           List<Editorial> editorial = editorialServicio.mostrarTodos();
           model.put("editorial", editorial);
           return "redirect:/verbajahtmleditorial#tablita"; 
        } catch (ErrorServicio e) {
            List<Editorial> editorial = editorialServicio.mostrarTodos();
            model.put("editorial", editorial);
            return "/editorial";
        }
    }
    
    @GetMapping("/verbajaeditorial")
    public String verbajaeditorial(ModelMap model) {
        return "redirect:/verbajahtml#tablita";
    }
    
    @GetMapping("/verbajahtmleditorial")
    public String verbajahtmleditorial(ModelMap model) {
        List<Editorial> editorial = editorialServicio.mostrarBaja();
        model.put("editorial", editorial);
        return "editorial.html";
    }
    
    @GetMapping("/veraltaeditorial")
    public String veraltaeditorial(ModelMap model) {
        return "redirect:/veraltahtmleditorial#tablita";
    }
    
    @GetMapping("/veraltahtmleditorial")
    public String veraltahtmleditorial(ModelMap model) {
        List<Editorial> editorial = editorialServicio.mostrarAlta();
        model.put("editorial", editorial);
        return "editorial.html";
    }
    
    @GetMapping("/vertodoseditorial")
    public String vertodoseditorial() {
        return "redirect:/vertodoshtmleditorial#tablita";
    }
    
    @GetMapping("/vertodoshtmleditorial")
    public String vertodoshtmleditorial(ModelMap model) {
        List<Editorial> editorial = editorialServicio.mostrarTodos();
        model.put("editorial", editorial);
        return "editorial.html";
    }
}
