package es.cic.curso25.proy008.Configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.cic.curso25.proy008.Service.BibliotecaNotFoundException;
import es.cic.curso25.proy008.Service.IdManualNoPermitidoException;



@RestControllerAdvice
public class MiControllerAdvice {

     @ExceptionHandler(BibliotecaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // Devuelve 404
    public String handleBibliotecaNotFound(BibliotecaNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(IdManualNoPermitidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Devuelve 400
    public String handleIdManualNoPermitido(IdManualNoPermitidoException ex) {
        return ex.getMessage();
    }
}

