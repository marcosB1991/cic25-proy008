package es.cic.curso25.proy008.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import es.cic.curso25.proy008.Model.Ciudad;

public interface CiudadRepository extends JpaRepository <Ciudad, Long> {

}
