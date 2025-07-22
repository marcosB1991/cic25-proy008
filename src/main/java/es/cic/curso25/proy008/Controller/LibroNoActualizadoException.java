package es.cic.curso25.proy008.Controller;

public class LibroNoActualizadoException extends RuntimeException {
    public LibroNoActualizadoException(){
        super();
    }

    public LibroNoActualizadoException(String mensaje){
        super(mensaje);
    }

}
