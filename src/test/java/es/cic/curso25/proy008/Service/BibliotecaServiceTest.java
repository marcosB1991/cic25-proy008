package es.cic.curso25.proy008.Service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.cic.curso25.proy008.Model.Biblioteca;
import es.cic.curso25.proy008.Repository.BibliotecaRepository;

public class BibliotecaServiceTest {

    @Mock //Anotacion de Mockito que crea un objeto simulado
    private BibliotecaRepository bibliotecaRepository;

    @InjectMocks //Inyecta el Mock (en mi caso el repositorio en mi bibliotecaService)
    private BibliotecaService bibliotecaService;

    private Biblioteca biblioteca;

    @BeforeEach //Marca que lo hará antes de cada test he decico poner aqui los datos de prueba de Biblioteca para mantener más limpio los test
    void setUp() {
        MockitoAnnotations.openMocks(this); //Inicia los Mocks anotados
        biblioteca = new Biblioteca();
        biblioteca.setId(1L);
        biblioteca.setNombre("Biblioteca Central");
        biblioteca.setDireccion("Calle Principal 123");
    }

    @Test
    void testListaBibliotecas() {
        List<Biblioteca> bibliotecas = Arrays.asList(biblioteca); //recibe la biblioteca creada en beforeEach como una lista
        when(bibliotecaRepository.findAll()).thenReturn(bibliotecas);

        List<Biblioteca> resultado = bibliotecaService.listaBibliotecas();

        assertEquals(1, resultado.size());
        assertEquals("Biblioteca Central", resultado.get(0).getNombre());
    }

    @Test
    void testBuscarPorIdExistente() {
        when(bibliotecaRepository.findById(1L)).thenReturn(Optional.of(biblioteca));

        Biblioteca resultado = bibliotecaService.buscarPorId(1L);

        assertNotNull(resultado); //Indica que espera que no sea null
        assertEquals("Biblioteca Central", resultado.getNombre());
    }

     @Test
    void testBuscarPorIdNoExistente() {
        when(bibliotecaRepository.findById(2L)).thenReturn(Optional.empty());

        //Al no existir ese id esperamos que salte la excepcion, invoca con una lamda la excepcion que se encuentra dentro del metodo buscarPorId
        assertThrows(BibliotecaNotFoundException.class, () -> {
            bibliotecaService.buscarPorId(2L);
        });
    }

    @Test
    void testCrearBibliotecaConIdExistente() {
        biblioteca.setId(10L); // Simula que tiene ID, lo cual no debería pasarse como parámetro

        assertThrows(IdManualNoPermitidoException.class, () -> {
            bibliotecaService.crearBiblioteca(biblioteca);
        });
    }

    
    @Test
    void testCrearBibliotecaCorrectamente() {
        biblioteca.setId(null); //Tengo que ponerlo para anular el id creado en el anterior test si no lo hago me va seguir tirando excepcion
        when(bibliotecaRepository.save(biblioteca)).thenReturn(biblioteca);

        Biblioteca resultado = bibliotecaService.crearBiblioteca(biblioteca);

        assertNotNull(resultado);
        assertEquals("Biblioteca Central", resultado.getNombre());
    }

    @Test
    void testEliminarBibliotecaExistente() {
        when(bibliotecaRepository.existsById(1L)).thenReturn(true);
        //Le indico que no hay que hacer nada una vez se elimina el test
        doNothing().when(bibliotecaRepository).deleteById(1L);

        //Verificamos que no se lanza excepcion la cual saltaria si la biblioteca no existiese
        assertDoesNotThrow(() -> bibliotecaService.eliminarBiblioteca(1L));
        //Verificacion final de que se usa realmente el metodo del repositorio
        verify(bibliotecaRepository).deleteById(1L);
    }


    @Test
    void testEliminarBibliotecaNoExistente() {
        when(bibliotecaRepository.existsById(2L)).thenReturn(false);

        //Capturamos la excepcion
        assertThrows(BibliotecaNotFoundException.class, () -> {
            bibliotecaService.eliminarBiblioteca(2L);
        });
    }

     @Test
    void testActualizarBibliotecaExistente() {
        Biblioteca actualizada = new Biblioteca();
        actualizada.setNombre("Biblioteca Nueva");
        actualizada.setDireccion("Calle Nueva 456");

        //Aquí devuelve el objeto creado en el @BeforeEach
        when(bibliotecaRepository.findById(1L)).thenReturn(Optional.of(biblioteca));

       //Simula procedimiento de guardar el creado en @BeforeEach
        when(bibliotecaRepository.save(any(Biblioteca.class))).thenReturn(biblioteca);

        //Acualizamos el original con los datos introducidos al inicio del test
        Biblioteca resultado = bibliotecaService.actualizarBiblioteca(1L, actualizada);

        //Comprobamos que la actualizacion ha sido la correcta 
        assertEquals("Biblioteca Nueva", resultado.getNombre());
        assertEquals("Calle Nueva 456", resultado.getDireccion());
    }

    @Test
    void testActualizarBibliotecaNoExistente() {
        Biblioteca actualizada = new Biblioteca();
        actualizada.setNombre("Nombre Falso");
        actualizada.setDireccion("Dirección Falsa");

        //Busca por id 4 como no existe devuelve empty
        when(bibliotecaRepository.findById(4L)).thenReturn(Optional.empty());

        assertThrows(BibliotecaNotFoundException.class, () -> {
            bibliotecaService.actualizarBiblioteca(4L, actualizada);
        });
    }
}
