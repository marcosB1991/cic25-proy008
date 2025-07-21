package es.cic.curso25.proy008.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import es.cic.curso25.proy008.Model.Biblioteca;
import es.cic.curso25.proy008.Repository.BibliotecaRepository;
@Service
public class BibliotecaService{

    private static final Logger LOGGER= LoggerFactory.getLogger(BibliotecaService.class);

    private final BibliotecaRepository bibliotecaRepository;

    public BibliotecaService(BibliotecaRepository bibliotecaRepository) {
        this.bibliotecaRepository = bibliotecaRepository;
    }

    public List<Biblioteca> listaBibliotecas(){
        return bibliotecaRepository.findAll();
    }


    //En momento de excepcion el Optional cambia a Biblioteca
    public Optional<Biblioteca> buscarPorId(Long id){
        return bibliotecaRepository.findById(id);
    }

    //Crea Biblioteca
    public Biblioteca crearBiblioteca(Biblioteca biblioteca){
        return bibliotecaRepository.save(biblioteca);
    }

    //Eliminar por Id

    public void eliminarBiblioteca(Long id){
        bibliotecaRepository.deleteById(id);
    }

  
    

    

}
