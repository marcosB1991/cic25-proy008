package es.cic.curso25.proy008.Configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class MiControllerAdvice {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    //@ExceptionHandler(ModificacionSecurityException.class)
    public void contoloModificacion(){

    }

}

