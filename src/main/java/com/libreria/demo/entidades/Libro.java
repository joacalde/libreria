
package com.libreria.demo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Libro {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Long isbn;
    private String titulo;
    private int anio;
    private int ejemplares;
    private int ejemplaresprestados;
    private int ejemplaresrestantes;
    private boolean alta;
    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Editorial editorial;

    public Libro() {
    }

    public String getId() {
        return id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public int getEjemplaresprestados() {
        return ejemplaresprestados;
    }

    public void setEjemplaresprestados(int ejemplaresprestados) {
        this.ejemplaresprestados = ejemplaresprestados;
    }

    public int getEjemplaresrestantes() {
        return ejemplaresrestantes;
    }

    public void setEjemplaresrestantes(int ejemplaresrestantes) {
        this.ejemplaresrestantes = ejemplaresrestantes;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", anio=" + anio + ", ejemplares=" + ejemplares + ", ejemplaresprestados=" + ejemplaresprestados + ", ejemplaresrestantes=" + ejemplaresrestantes + ", alta=" + alta + ", autor=" + autor + ", editorial=" + editorial + '}';
    }
    
    
}
