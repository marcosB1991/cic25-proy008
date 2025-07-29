package es.cic.curso25.proy008.Controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import es.cic.curso25.proy008.Service.EditorialService;
import es.cic.curso25.proy008.Service.LibroService;
import es.cic.curso25.proy008.Model.Editorial;
import es.cic.curso25.proy008.Model.Libro;


@RestController
@RequestMapping("/libro")
//añadir normalemnete en el metodo controller
//en la clase que recibe el objeto tienes que escribir @Validate *** delante del body
public class LibroController {
     private static final Logger LOGGER = LoggerFactory.getLogger(LibroController.class);


    @Autowired
    private LibroService libroService;
    @Autowired
    private EditorialService editorialService;



    //crear un libro (objeto)
    @PostMapping
    //podemos aplicar notacion @RespondeStatus delante de los propios metodos en el controller
    //@ResponseStatus(HttpStatus.CREATED) //solo salta si funciona correctamente
    //*** 
    public Libro create (@RequestBody(required = true) Libro libro){
         LOGGER.info("Crear un libro"+ libro);
         
        if (libro.getId()!= null){
            throw new LibroNoCreadoException(libro);
        }
        return libroService.create(libro);

    }
    //public ResponseEntity <Libro> create (@RequestBody(required = true) Libro libro){
        // LOGGER.info("Crear un libro"+ libro);
         
        //if (libro.getId()!= null){
          //  throw new LibroNoCreadoException(libro);
        //}
        //libro =  libroService.create(libro);
       //return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(libro);

    //}

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
    public List<Libro> getAnioDePublicacion(@PathVariable int anioDePublicacion){
        LOGGER.info("Obtener los libros publicados en el año"+anioDePublicacion);
        return libroService.getAnioDePublicacion(anioDePublicacion);
    }

    //Actualiza un registro de libro
    @PutMapping
    //*** 
    public Libro update (@RequestBody Libro libro){
        LOGGER.info("Actualizar el libro"+libro);
         if (libro.getId() == null){
            
            throw new LibroNoActualizadoException("No se ha podido actualizar el libro");
        }
        
        return libroService.update(libro);
    }

    //Borrra un libro por id
    @DeleteMapping("/{id}")  
    public void delete(@PathVariable long id){
        LOGGER.info("Eliminar el libro con id" + id);
        libroService.delete(id);
    }

    @PostMapping("/editorial")
    //*** 
    public Editorial create(@RequestBody Editorial editorial){

        Editorial editorialCreada = editorialService.create(editorial);

        return editorialCreada;
    }

}
