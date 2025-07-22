package es.cic.curso25.proy008.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.cic.curso25.proy008.Service.LibroService;
import es.cic.curso25.proy008.Model.Libro;


@RestController
@RequestMapping("/libro")
public class LibroController {
     private static final Logger LOGGER = LoggerFactory.getLogger(LibroController.class);


    @Autowired
    private LibroService libroService;



    //crear un libro (objeto)
    @PostMapping
    public Libro create (@RequestBody(required = true) Libro libro){
         LOGGER.info("Crear un libro"+ libro);

        //if(libro.getId()!= null){
            //throw new ModificacionSecurityException("Necesito mensaje");

        //}
        return libroService.create(libro);

    }

    //obtener todos los libros
     @GetMapping
    public List<Libro> get(){
        LOGGER.info("Hacer una lista con los libros o");
        return libroService.get();
    }
    //Obtener el libro con id...
    @GetMapping("/{id}")
    public Libro getId(@PathVariable Long id){
        LOGGER.info("Obtener un libro por id"+id);
        return libroService.getId(id);
    }  
    //Obtiene los libros por el nombre del autor
    @GetMapping("/autor/{autor}")
    public List<Libro> getAutor(@PathVariable String autor){
        LOGGER.info("Obtener los libros escritos por el autor"+autor);
        return libroService.getAutor(autor);
    } 
    //Obtiene los libros por el nombre del libro 
    @GetMapping("/nombreLibro/{nombreLibro}")
    public List<Libro> getNombreLibro(@PathVariable String nombreLibro){
        LOGGER.info("Obtener los libros por el titulo"+nombreLibro);
        return libroService.getNombreLibro(nombreLibro);
    }
    //Obtiene los libros creados en el año ...
    @GetMapping("/añoDePublicacion/{añoDePublicacion}")
    public List<Libro> getAñoDePublicacion(@PathVariable int añoDePublicacion){
        LOGGER.info("Obtener los libros publicados en el año"+añoDePublicacion);
        return libroService.getAñoDePublicacion(añoDePublicacion);
    }

    //Actualiza un registro de libro
    @PutMapping("/{id}")
    public Libro update (@RequestBody Libro libro){
        LOGGER.info("Actualizar el libro"+libro);
        return libroService.update(libro);
    }

    //Borrra un libro por id
    @DeleteMapping("/{id}")  
    public void delete(@PathVariable long id){
        LOGGER.info("Eliminar el libro con id" + id);
        libroService.delete(id);
    }



}
