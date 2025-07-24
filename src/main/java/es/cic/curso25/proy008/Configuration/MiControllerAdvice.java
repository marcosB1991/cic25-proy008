package es.cic.curso25.proy008.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.cic.curso25.proy008.Controller.LibroNoCreadoException;
import es.cic.curso25.proy008.Controller.EditorialNoActializadaException;
import es.cic.curso25.proy008.Controller.EditorialNoCreadaException;
import es.cic.curso25.proy008.Controller.LibroNoActualizadoException;
import es.cic.curso25.proy008.Service.BibliotecaNotFoundException;
import es.cic.curso25.proy008.Service.IdManualNoPermitidoException;

@RestControllerAdvice
public class MiControllerAdvice {
     //private static final Logger LOGGER = LoggerFactory.getLogger(MiControllerAdvice.class);

    private static final Logger LOGGER = LoggerFactory.getLogger(MiControllerAdvice.class);

    @ExceptionHandler(BibliotecaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Devuelve 404
    public String handleBibliotecaNotFound(BibliotecaNotFoundException ex) {
        LOGGER.warn("{}", ex.getMessage());
        ex.printStackTrace();
        return ex.getMessage();
    }

    @ExceptionHandler(IdManualNoPermitidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Devuelve 400
    public String handleIdManualNoPermitido(IdManualNoPermitidoException ex) {
        LOGGER.error("Intento de creación con ID manual: {}", ex.getMessage());
        ex.printStackTrace();
        return ex.getMessage();
    }
    @ExceptionHandler(LibroNoActualizadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleLibroNoActualizado(LibroNoActualizadoException ex){
        LOGGER.error("El libro no se ha podido actualizar y se lanza la excepción", ex);
        ex.printStackTrace();
        return ex.getMessage();
    }
    @ExceptionHandler(LibroNoCreadoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleLibroNoCreado(LibroNoCreadoException ex){

        LOGGER.error("El libro no se ha podido crear y se lanza la excepción", ex);
        ex.printStackTrace();
        return ex.getMessage();
    }
    // Manejador genérico para cualquier excepción no controlada
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Devuelve 500
    public String handleGeneralException(Exception ex) {
        LOGGER.error("Error inesperado: {}", ex.getMessage(), ex);
        ex.printStackTrace();
        return "Ha ocurrido un error interno en el servidor.";
    }
    @ExceptionHandler(EditorialNoActializadaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEditorialNoActualizada(EditorialNoActializadaException ex){
        LOGGER.error("No se ha podido actualizar la informacion de la editorial y se lanza la excepción", ex);
        ex.printStackTrace();
        return ex.getMessage();
    }
    @ExceptionHandler(EditorialNoCreadaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEditorialNoCreada(EditorialNoCreadaException ex){
        LOGGER.error("No se ha podido crear la editorial y se lanza la excepción", ex);
        ex.printStackTrace();
        return ex.getMessage();
    }
}

