package es.cic.curso25.proy008.Service;

public class LibroNoEliminadoException extends RuntimeException {
    public LibroNoEliminadoException(long id){
        super("No se puede eliminar el libro con id"+ id);
    }

}
