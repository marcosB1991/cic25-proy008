package es.cic.curso25.proy008.Service;

public class IdManualNoPermitidoException extends RuntimeException {

    public IdManualNoPermitidoException() {
        super("No está permitido asignar un ID manualmente al crear una Biblioteca.");
    }

    public IdManualNoPermitidoException(String message) {
        super(message);
    }
}
