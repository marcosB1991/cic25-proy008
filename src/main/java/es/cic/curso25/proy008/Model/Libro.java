package es.cic.curso25.proy008.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreLibro;
    private String autor;
    private int añoDePublicacion;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombreLibro() {
        return nombreLibro;
    }
    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getAñoDePublicacion() {
        return añoDePublicacion;
    }
    public void setAñoDePublicacion(int añoDePublicacion) {
        this.añoDePublicacion = añoDePublicacion;
    }
    
}
