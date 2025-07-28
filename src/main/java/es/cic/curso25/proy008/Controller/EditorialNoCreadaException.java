package es.cic.curso25.proy008.Controller;

import es.cic.curso25.proy008.Model.Editorial;


public class EditorialNoCreadaException extends RuntimeException {
        public EditorialNoCreadaException(Editorial editorial){
        super("No se puede crear la editorial"+ editorial);
    }
}
