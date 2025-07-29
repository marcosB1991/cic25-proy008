package es.cic.curso25.proy008.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cic.curso25.proy008.Model.Editorial;


public interface EditorialRepository extends JpaRepository<Editorial,Long>{

    public Optional<Editorial> findById(Long id);
    public List<Editorial> findByNombreEditorial(String nombreLibro);
    public List<Editorial> findByNumeroEdiciones(int numeroEdiciones);

}
