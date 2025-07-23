package es.cic.curso25.proy008.Service;

public class CiudadNotFoundException extends RuntimeException {
    public CiudadNotFoundException(Long id) {
        super("No se encontró la ciudad con id " + id);
    }
}
