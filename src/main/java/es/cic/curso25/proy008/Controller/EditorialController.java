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
import es.cic.curso25.proy008.Model.Editorial;
import es.cic.curso25.proy008.Service.EditorialService;


@RestController
@RequestMapping("/editorial")
public class EditorialController {
       private static final Logger LOGGER = LoggerFactory.getLogger(EditorialController.class);


    @Autowired
    private EditorialService editorialService;



    //crear una editorial (objeto)
    @PostMapping
    public Editorial create (@RequestBody(required = true) Editorial editorial){
         LOGGER.info("Crear una editorial"+ editorial);
         
        if (editorial.getId()!=null){
           throw new EditorialNoCreadaException(editorial);
        }
        return editorialService.create(editorial);

    }

    //obtener todas las editoriales
     @GetMapping
    public List<Editorial> get(){
        LOGGER.info("Hacer una lista con las editoriales ");
        return editorialService.get();
    }
    //Obtener la editorial con id...
    @GetMapping("/{id}")
    public Editorial getId(@PathVariable Long id){
        LOGGER.info("Obtener una editorial por id"+id);
        return editorialService.getId(id);
    }  
    //Obtiene las editoriales por su nombre
    @GetMapping("/nombreEditorial/{nombreEditorial}")
    public List<Editorial> getNombreEditorial(@PathVariable String nombreEditorial){
        LOGGER.info("Obtener las editoriales con el nombre"+nombreEditorial);
        return editorialService.getNombreEditorial(nombreEditorial);
    } 
    //Obtiene el numero de ediciones que tienen las editoriales
    @GetMapping("/numeroEdiciones/{numeroEdiciones}")
    public List<Editorial> getNumeroEdiciones(@PathVariable int numeroEdiciones){
        LOGGER.info("Obtener el numero de ediciones"+numeroEdiciones);
        return editorialService.getNumeroEdiciones(numeroEdiciones);
    }

    //Actualiza 
    @PutMapping
    public Editorial update (@RequestBody Editorial editorial){
        LOGGER.info("Actualizar la editorial"+editorial);
         if (editorial.getId() == null){
            
            throw new EditorialNoActializadaException("No se ha podido actualizar la editorial");
        }
        
        return editorialService.update(editorial);
    }

    //Borrra la editorial con id
    @DeleteMapping("/{id}")  
    public void delete(@PathVariable long id){
        LOGGER.info("Eliminar la editorial con id" + id);
        editorialService.delete(id);
    }


}
