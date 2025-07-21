package es.cic.curso25.proy008.Controller;

import es.cic.curso25.proy008.Model.Biblioteca;
import es.cic.curso25.proy008.Service.BibliotecaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bibliotecas")
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    public BibliotecaController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    // GET: Lista todas las bibliotecas
    @GetMapping
    public List<Biblioteca> listarTodas() {
        return bibliotecaService.listaBibliotecas();
    }

    // GET: Buscar biblioteca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> buscarPorId(@PathVariable Long id) {
        Optional<Biblioteca> biblioteca = bibliotecaService.buscarPorId(id);
        return biblioteca.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Crear una nueva biblioteca
    @PostMapping
    public ResponseEntity<Biblioteca> crearBiblioteca(@RequestBody Biblioteca biblioteca) {
        Biblioteca nuevaBiblioteca = bibliotecaService.crearBiblioteca(biblioteca);
        return ResponseEntity.ok(nuevaBiblioteca);
    }

    // PUT: Actualizar biblioteca existente
    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> actualizarBiblioteca(@PathVariable Long id, @RequestBody Biblioteca biblioteca) {
        Biblioteca actualizada = bibliotecaService.actualizarBiblioteca(id, biblioteca);
        return ResponseEntity.ok(actualizada);
    }

    // DELETE: Eliminar una biblioteca por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBiblioteca(@PathVariable Long id) {
        bibliotecaService.eliminarBiblioteca(id);
        return ResponseEntity.noContent().build();
    }
}
