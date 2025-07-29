package es.cic.curso25.proy008.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import es.cic.curso25.proy008.Model.Libro;

public interface LibroRepository extends JpaRepository<Libro,Long>{

    //@Query("Select id from Libro")
    public Optional<Libro> findById(Long id);
    public List<Libro> findByNombreLibro(String nombreLibro);
    public List<Libro> findByAutor(String autor);
    public List<Libro> findByAnioDePublicacion(int añoDePublicacion);
}


