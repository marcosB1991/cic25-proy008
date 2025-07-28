package es.cic.curso25.proy008.Controller;

public class EditorialNoActializadaException extends RuntimeException{
    public EditorialNoActializadaException(){
        super();
    }

    public EditorialNoActializadaException(String mensaje){
        super(mensaje);
    }


}
