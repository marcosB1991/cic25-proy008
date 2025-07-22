package es.cic.curso25.proy008.Controller;

import es.cic.curso25.proy008.Model.Libro;

public class LibroNoCreadoException extends RuntimeException {
        public LibroNoCreadoException(Libro libro){
        super("No se puede crear el libro"+ libro);
    }

}

