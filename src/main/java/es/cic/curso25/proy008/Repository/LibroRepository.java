package es.cic.curso25.proy008.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cic.curso25.proy008.Model.Libro;

public interface LibroRepository extends JpaRepository<Libro,Long>{
  
    Optional<Libro> findById(Long id);
    List<Libro> findByNombreLibro(String nombreLibro);
    List<Libro> findByAutor(String autor);
    List<Libro> findByAñoDePublicacion(int añoDePublicacion);
}


