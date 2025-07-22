package es.cic.curso25.proy008.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso25.proy008.Controller.LibroNoCreadoException;
import es.cic.curso25.proy008.Controller.LibroNoActualizadoException;
import es.cic.curso25.proy008.Model.Libro;
import es.cic.curso25.proy008.Repository.LibroRepository;

@Service
public class LibroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibroService.class);

    @Autowired
    private LibroRepository libroRepository;

    
    //crea un libro
    public Libro create(Libro libro){

        LOGGER.info("Creo el libro" + libro);
        if (libro.equals(null)){
            throw new LibroNoCreadoException(libro);
        }
        return libroRepository.save(libro);
    }


    //lista todas los libros
    public List<Libro> get(){

        LOGGER.info("Obtengo todos los libros");
        return libroRepository.findAll();
    }

    //Obtiene un libro por id
    public Libro getId(Long id){
        LOGGER.info("Obtengo el libro con el id" + id);
        return libroRepository.findById(id).orElse(null);
    }

    //Obtiene un listado de libros por nombre del autor
    public List<Libro> getAutor(String autor){
        LOGGER.info("Obtengo el libro por el autor" + autor);
        return libroRepository.findByAutor(autor);
    }

    //Obtiene un listado de libros por el nombre del libro
    public List<Libro> getNombreLibro(String nombreLibro){
        LOGGER.info("Obtengo el nombre del libro"+nombreLibro);
        return libroRepository.findByNombreLibro(nombreLibro);
    }

    //Obtiene un listado de libros publicados en el año
    public List<Libro> getAñoDePublicacion(int añoDePublicacion){
        LOGGER.info("El año de publicacion del libro es"+ añoDePublicacion);
        return libroRepository.findByAñoDePublicacion(añoDePublicacion);
    }


    //Actualiza un libro
    public Libro update(Libro libro){
        LOGGER.info("Actualización"+ libro);
        return libroRepository.save(libro);
    }

    
    //Borra un libro por id
    public void delete(long id){
        LOGGER.info("Eliminando el libro con id"+ id);

        libroRepository.deleteById(id);
    }




}
