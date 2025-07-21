package es.cic.curso25.proy008.Service;

public class BibliotecaNotFoundException extends RuntimeException {
    public BibliotecaNotFoundException(Long id) {
        super("No se encontró la biblioteca con ID: " + id);
    }
}
