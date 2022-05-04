package com.libreria.demo.controladores;

import com.libreria.demo.entidades.Autor;
import com.libreria.demo.entidades.Editorial;
import com.libreria.demo.entidades.Libro;
import com.libreria.demo.errores.ErrorServicio;
import com.libreria.demo.servicios.AutorServicio;
import com.libreria.demo.servicios.EditorialServicio;
import com.libreria.demo.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LibroControlador {
    
    @Autowired
    private EditorialServicio editorialServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private LibroServicio libroServicio;
    
    @GetMapping("/libro")
    public String libro(ModelMap model) {
        List<Editorial> editorial = editorialServicio.mostrarAlta();
        model.put("editorial", editorial);
        List<Autor> autor = autorServicio.mostrarAlta();
        model.put("autor", autor);
        List<Libro> libro = libroServicio.mostrarTodos();
        model.put("libro", libro);
        return "libro.html";
    }
    
    @PostMapping("/libroRegistrar")
    public String libroRegistrar(ModelMap model, @RequestParam Long isbnLibro, @RequestParam String tituloLibro, @RequestParam int anioLibro, @RequestParam int ejemplaresLibro, @RequestParam int ejemplaresprestadosLibro, @RequestParam String autorLibro, @RequestParam String editorialLibro){
        
        try {
            Autor autor = autorServicio.buscarPorNombre(autorLibro);
            Editorial editorial = editorialServicio.buscarPorNombre(editorialLibro);  
                  
            libroServicio.registrar(isbnLibro, tituloLibro, anioLibro, ejemplaresLibro, ejemplaresprestadosLibro, autor, editorial);

            return "redirect:/libro#tablita";
        } catch (ErrorServicio e) {
            model.put("mensaje", e.getMessage());
            return "/libro";
        }
    }
    
    @GetMapping("/eliminarlibro/{id}")
    public String eliminarLibro(ModelMap model, @PathVariable("id") String id) throws ErrorServicio{
        try {
           libroServicio.borrarPorId(id);
           return "redirect:/libro#tablita";
        } catch (ErrorServicio e) {
            model.put("mensaje", e.getMessage());
            return "/libro"; 
        }
    }
    
    @GetMapping("/agregarejemplares/{id}")
    public String agregarejemplares(ModelMap model, @PathVariable("id") String id) throws ErrorServicio{
        try {
           libroServicio.agregarejemplares(id);
           return "redirect:/libro#tablita";
        } catch (ErrorServicio e) {
            model.put("mensaje", e.getMessage());
            return "/libro"; 
        }
    }
    
    @GetMapping("/sacarejemplares/{id}")
    public String sacarejemplares(ModelMap model, @PathVariable("id") String id) throws ErrorServicio{
        try {
           libroServicio.sacarejemplares(id);
           return "redirect:/libro#tablita";
        } catch (ErrorServicio e) {
            model.put("mensaje", e.getMessage());
            return "/libro"; 
        }
    }
    
    
}
    
