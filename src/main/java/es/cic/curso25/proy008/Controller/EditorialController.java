package es.cic.curso25.proy008.Controller;

import java.util.List;
import java.util.Optional;

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

import es.cic.curso25.proy008.Model.Editorial;
import es.cic.curso25.proy008.Model.Libro;
import es.cic.curso25.proy008.Repository.EditorialRepository;
import es.cic.curso25.proy008.Service.LibroService;

@RestController
@RequestMapping("/editorial")
public class EditorialController {
       private static final Logger LOGGER = LoggerFactory.getLogger(EditorialController.class);


    @Autowired
    private EditorialRepository editorialRepository;



    //crear una editorial (objeto)
    @PostMapping
    public Editorial create (@RequestBody(required = true) Editorial editorial){
         LOGGER.info("Crear una editorial"+ editorial);
         
        //if (editorial.equals(null)){
        //    throw new EditorialNoCreadaException(editorial);
       // }
        return editorialRepository.save(editorial);

    }

    //obtener todas las editoriales
     @GetMapping
    public List<Editorial> get(){
        LOGGER.info("Hacer una lista con las editoriales ");
        return editorialRepository.findAll();
    }
    //Obtener la editorial con id...
    @GetMapping("/{id}")
    public Optional<Editorial> getId(@PathVariable Long id){
        LOGGER.info("Obtener una editorial por id"+id);
        return editorialRepository.findById(id);
    }  
    //Obtiene las editoriales por su nombre
    @GetMapping("/nombreEditorial/{nombreEditorial}")
    public List<Editorial> getNombreEditorial(@PathVariable String nombreEditorial){
        LOGGER.info("Obtener las editoriales con el nombre"+nombreEditorial);
        return editorialRepository.findByNombreEditorial(nombreEditorial);
    } 
    //Obtiene el numero de ediciones que tienen las editoriales
    @GetMapping("/numeroEdiciones/{numeroEdiciones}")
    public List<Editorial> getNumeroEdiciones(@PathVariable int numeroEdiciones){
        LOGGER.info("Obtener el numero de ediciones"+numeroEdiciones);
        return editorialRepository.findByNumeroEdiciones(numeroEdiciones);
    }

    //Actualiza 
    @PutMapping("/{id}")
    public Editorial update (@RequestBody Editorial editorial){
        LOGGER.info("Actualizar la editorial"+editorial);
         //if (editorial.getId() == null){
            
          //  throw new EditorialNoActualizadoException("No se ha podido actualizar la editorial");
        //}
        
        return editorialRepository.save(editorial);
    }

    //Borrra la editorial con id
    @DeleteMapping("/{id}")  
    public void delete(@PathVariable long id){
        LOGGER.info("Eliminar la editorial con id" + id);
        editorialRepository.deleteById(id);
    }


}
