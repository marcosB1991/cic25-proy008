package es.cic.curso25.proy008.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cic.curso25.proy008.Model.Ciudad;

public interface CiudadRepository extends JpaRepository <Ciudad, Long> {

}
