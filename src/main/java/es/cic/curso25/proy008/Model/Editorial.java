package es.cic.curso25.proy008.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreEditorial;
    private int numeroEdiciones;

    
    @OneToOne(mappedBy = "editorial")
    private Libro libro;
     

    public Libro getLibro() {
        return libro;
    }
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    public Long getId() {
        return id;
    }
     public void setId(Long id) {
        this.id = id;
    }
    public String getNombreEditorial() {
        return nombreEditorial;
    }
    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }
    public int getNumeroEdiciones() {
        return numeroEdiciones;
    }
    public void setNumeroEdiciones(int numeroEdiciones) {
        this.numeroEdiciones = numeroEdiciones;
    }
     @Override
    public String toString() {
        return "Editorial [id=" + id + ", nombreEditorial=" + nombreEditorial + ", numeroEdiciones=" + numeroEdiciones
                + "]";
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
        Editorial other = (Editorial) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    

}
