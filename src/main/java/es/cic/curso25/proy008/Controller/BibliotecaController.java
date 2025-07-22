package es.cic.curso25.proy008.Controller;

import es.cic.curso25.proy008.Model.Biblioteca;
import es.cic.curso25.proy008.Service.BibliotecaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bibliotecas")
public class BibliotecaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BibliotecaController.class);

    private final BibliotecaService bibliotecaService;

    public BibliotecaController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    // GET: Lista todas las bibliotecas
    @GetMapping
    public List<Biblioteca> listarTodas() {
        LOGGER.info("Petición GET /bibliotecas");
        return bibliotecaService.listaBibliotecas();
    }

    // GET: Buscar biblioteca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> buscarPorId(@PathVariable Long id) {
        LOGGER.info("Petición GET /bibliotecas/{}", id);
        Biblioteca biblioteca = bibliotecaService.buscarPorId(id);
        return ResponseEntity.ok(biblioteca);
    }

    // POST: Crear una nueva biblioteca
    @PostMapping
    public ResponseEntity<Biblioteca> crearBiblioteca(@RequestBody Biblioteca biblioteca) {
         LOGGER.info("Petición POST /bibliotecas con datos: {}", biblioteca);
        Biblioteca nuevaBiblioteca = bibliotecaService.crearBiblioteca(biblioteca);
        return ResponseEntity.ok(nuevaBiblioteca);
    }

    // PUT: Actualizar biblioteca existente
    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> actualizarBiblioteca(@PathVariable Long id, @RequestBody Biblioteca biblioteca) {
         LOGGER.info("Petición PUT /bibliotecas/{} con datos: {}", id, biblioteca);
        Biblioteca actualizada = bibliotecaService.actualizarBiblioteca(id, biblioteca);
        return ResponseEntity.ok(actualizada);
    }

    // DELETE: Eliminar una biblioteca por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBiblioteca(@PathVariable Long id) {
        LOGGER.info("Petición DELETE /bibliotecas/{}", id);
        bibliotecaService.eliminarBiblioteca(id);
        return ResponseEntity.noContent().build();
    }
}
