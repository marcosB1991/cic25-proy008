package es.cic.curso25.proy008.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cic.curso25.proy008.Model.Libro;

public interface LibroRepository extends JpaRepository<Libro,Long>{

}
