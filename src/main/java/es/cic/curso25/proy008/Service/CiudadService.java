package es.cic.curso25.proy008.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso25.proy008.Model.Ciudad;
import es.cic.curso25.proy008.Repository.CiudadRepository;

@Service
@Transactional
public class CiudadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CiudadService.class);

    private final CiudadRepository ciudadRepository;

    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    // Listar todas las ciudades
    @Transactional(readOnly = true)
    public List<Ciudad> listaCiudades() {
        LOGGER.info("Listando todas las ciudades");
        return ciudadRepository.findAll();
    }

    // Buscar ciudad por id
    @Transactional(readOnly = true)
    public Ciudad buscarPorId(Long id) {
        LOGGER.info("Buscando ciudad con id {}", id);
        return ciudadRepository.findById(id)
                .orElseThrow(() -> new CiudadNotFoundException(id));
    }

    // Crear ciudad
    public Ciudad crearCiudad(Ciudad ciudad) {
        if (ciudad.getId() != null && ciudad.getId() != 0) {
            throw new IdManualNoPermitidoException();
        }
        LOGGER.info("Creando nueva ciudad {}", ciudad.getNombre());
        return ciudadRepository.save(ciudad);
    }

    // Eliminar ciudad por id
    public void eliminarCiudad(Long id) {
        LOGGER.info("Eliminando ciudad con id {}", id);
        if (!ciudadRepository.existsById(id)) {
            throw new CiudadNotFoundException(id);
        }
        ciudadRepository.deleteById(id);
    }

    // Actualizar ciudad existente
    public Ciudad actualizarCiudad(Long id, Ciudad ciudadActualizada) {
        LOGGER.info("Actualizando ciudad con id {}", id);
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(() -> new CiudadNotFoundException(id));

        ciudad.setNombre(ciudadActualizada.getNombre());

        return ciudadRepository.save(ciudad);
    }
}
