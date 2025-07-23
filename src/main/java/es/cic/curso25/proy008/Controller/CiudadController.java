package es.cic.curso25.proy008.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import es.cic.curso25.proy008.Model.Biblioteca;
import es.cic.curso25.proy008.Model.Ciudad;
import es.cic.curso25.proy008.Service.BibliotecaService;
import es.cic.curso25.proy008.Service.CiudadService;

@RestController
@RequestMapping("/ciudad")
public class CiudadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CiudadController.class);

    private final CiudadService ciudadService;
    private final BibliotecaService bibliotecaService;

    public CiudadController(CiudadService ciudadService, BibliotecaService bibliotecaService) {
        this.ciudadService = ciudadService;
        this.bibliotecaService = bibliotecaService;
    }


     @GetMapping("/{id}")
    public Ciudad get(@PathVariable Long id) {
        LOGGER.info("Buscando la ciudad con id {}", id);
        return ciudadService.buscarPorId(id);
    }

    @GetMapping
    public List<Ciudad> get() {
        LOGGER.info("Buscando todas las ciudades");
        return ciudadService.listaCiudades();
    }

    @PostMapping
    public Ciudad create(@RequestBody Ciudad ciudad) {
        LOGGER.info("Creando una ciudad");
        return ciudadService.crearCiudad(ciudad);
    }

    @PutMapping("/{id}")
    public Ciudad update(@PathVariable Long id, @RequestBody Ciudad ciudadActualizada) {
        LOGGER.info("Actualizando la ciudad con id {}", id);
        return ciudadService.actualizarCiudad(id, ciudadActualizada);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        LOGGER.info("Eliminando la ciudad con id {}", id);
        ciudadService.eliminarCiudad(id);
    }

  
    // Relación Ciudad - Biblioteca
    

    /**
     * Asignar una biblioteca a una ciudad.
     * Si la biblioteca ya existe, se le asocia la ciudad indicada.
     * 
     * Ejemplo JSON para POST:
     * {
     *   "nombre": "Biblioteca Central",
     *   "direccion": "Av. Principal 123"
     * }
     */
    @PostMapping("/{idCiudad}/biblioteca")
    public Biblioteca asignarBiblioteca(@PathVariable Long idCiudad, @RequestBody Biblioteca biblioteca) {
        LOGGER.info("Asignando biblioteca {} a la ciudad con id {}", biblioteca.getNombre(), idCiudad);

        // Buscar la ciudad
        Ciudad ciudad = ciudadService.buscarPorId(idCiudad);

        // Asociar
        biblioteca.setCiudad(ciudad);
        ciudad.setBiblioteca(biblioteca);

        return bibliotecaService.crearBiblioteca(biblioteca);
    }
}
