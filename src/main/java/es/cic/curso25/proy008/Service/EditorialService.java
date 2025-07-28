package es.cic.curso25.proy008.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.cic.curso25.proy008.Model.Editorial;
import es.cic.curso25.proy008.Repository.EditorialRepository;

@Service
@Transactional
public class EditorialService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EditorialService.class);

    @Autowired
    private EditorialRepository editorialRepository;
    
    //crea la editorial
    public Editorial create(Editorial editorial){

        LOGGER.info("creo la editorial" + editorial);
        
        return editorialRepository.save(editorial);
    }

    @Transactional(readOnly = true)
    //lista de las editoriales
    public List<Editorial> get(){

        LOGGER.info("Obtengo todas la editoriales");
        return editorialRepository.findAll();
    }

    //Obtiene la editorial por id
    public Editorial getId(Long id){
        LOGGER.info("Obtengo la editorial con el id" + id);
        return editorialRepository.findById(id).orElse(null);
    }

    //Obtiene un listado las editoriales por nombre
    public List<Editorial> getNombreEditorial(String nombreEditorial){
        LOGGER.info("Obtengo el nombre de Editorial" + nombreEditorial);
        return editorialRepository.findByNombreEditorial("nombreEditorial");
    }

    //Obtiene un listado de la cantidad de publicaciones de la editorial
    public List<Editorial> getNumeroEdiciones(int numeroEdiciones){
        LOGGER.info("El numero de ediciones es"+ numeroEdiciones);
        return editorialRepository.findByNumeroEdiciones(numeroEdiciones);
    }


    //Actualizacion de la editorial
    public Editorial update(Editorial editorial){
        LOGGER.info("Actualización"+ editorial);
        return editorialRepository.save(editorial);
    }

    
    //Borra la editorial por id
    public void delete(long id){
        LOGGER.info("Eliminando la editorial con id"+ id);

        editorialRepository.deleteById(id);
    }
}


