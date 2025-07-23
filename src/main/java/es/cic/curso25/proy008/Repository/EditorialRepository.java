package es.cic.curso25.proy008.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cic.curso25.proy008.Model.Editorial;


public interface EditorialRepository extends JpaRepository<Editorial,Long>{

    Optional<Editorial> findById(Long id);
    List<Editorial> findByNombreEditorial(String nombreLibro);
    List<Editorial> findByNumeroEdiciones(int numeroEdiciones);

}
