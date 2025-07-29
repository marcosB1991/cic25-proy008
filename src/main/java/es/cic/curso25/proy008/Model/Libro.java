package es.cic.curso25.proy008.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreLibro;
    private String autor;
    private int anioDePublicacion;

   
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "editorial_id") 
    private Editorial editorial;


    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE},
                fetch = FetchType.EAGER
                )
    private List<Comprador> compradores = new ArrayList<>();

    public Editorial getEditorial() {
        return editorial;
    }
    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
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
    public int getAnioDePublicacion() {
        return anioDePublicacion;
    }
    public void setAnioDePublicacion(int añoDePublicacion) {
        this.anioDePublicacion = añoDePublicacion;
    }
    @Override
    public String toString() {
        return "Libro [id=" + id + ", nombreLibro=" + nombreLibro + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Libro other = (Libro) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }   
    public List<Comprador> getCompradores() {
        return compradores;
    }
    public void setCompradores(List<Comprador> compradores) {
        this.compradores = compradores;
    }
}
