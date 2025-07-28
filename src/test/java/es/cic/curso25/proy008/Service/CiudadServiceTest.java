package es.cic.curso25.proy008.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.cic.curso25.proy008.Model.Ciudad;
import es.cic.curso25.proy008.Repository.CiudadRepository;

public class CiudadServiceTest {

    @Mock // Crea un mock simulado del repositorio
    private CiudadRepository ciudadRepository;

    @InjectMocks // Inyecta el mock en el service real
    private CiudadService ciudadService;

    private Ciudad ciudad;

    @BeforeEach // Se ejecuta antes de cada test
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicia los mocks anotados
        ciudad = new Ciudad();
        ciudad.setId(1L);
        ciudad.setNombre("Ciudad Central");
    }

    @Test
    void testListaCiudades() {
        List<Ciudad> ciudades = Arrays.asList(ciudad);
        when(ciudadRepository.findAll()).thenReturn(ciudades);

        List<Ciudad> resultado = ciudadService.listaCiudades();

        assertEquals(1, resultado.size());
        assertEquals("Ciudad Central", resultado.get(0).getNombre());
    }

    @Test
    void testBuscarPorIdExistente() {
        when(ciudadRepository.findById(1L)).thenReturn(Optional.of(ciudad));

        Ciudad resultado = ciudadService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Ciudad Central", resultado.getNombre());
    }

    @Test
    void testBuscarPorIdNoExistente() {
        when(ciudadRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(CiudadNotFoundException.class, () -> {
            ciudadService.buscarPorId(2L);
        });
    }

    @Test
    void testCrearCiudadConIdExistente() {
        ciudad.setId(10L); // Simulamos una ciudad con ID manual

        assertThrows(IdManualNoPermitidoException.class, () -> {
            ciudadService.crearCiudad(ciudad);
        });
    }

    @Test
    void testCrearCiudadCorrectamente() {
        ciudad.setId(null); // Reseteamos el ID
        when(ciudadRepository.save(ciudad)).thenReturn(ciudad);

        Ciudad resultado = ciudadService.crearCiudad(ciudad);

        assertNotNull(resultado);
        assertEquals("Ciudad Central", resultado.getNombre());
    }

    @Test
    void testEliminarCiudadExistente() {
        when(ciudadRepository.existsById(1L)).thenReturn(true);
        doNothing().when(ciudadRepository).deleteById(1L);

        assertDoesNotThrow(() -> ciudadService.eliminarCiudad(1L));
        verify(ciudadRepository).deleteById(1L);
    }

    @Test
    void testEliminarCiudadNoExistente() {
        when(ciudadRepository.existsById(2L)).thenReturn(false);

        assertThrows(CiudadNotFoundException.class, () -> {
            ciudadService.eliminarCiudad(2L);
        });
    }

    @Test
    void testActualizarCiudadExistente() {
        Ciudad actualizada = new Ciudad();
        actualizada.setNombre("Ciudad Nueva");

        when(ciudadRepository.findById(1L)).thenReturn(Optional.of(ciudad));
        when(ciudadRepository.save(any(Ciudad.class))).thenReturn(ciudad);

        Ciudad resultado = ciudadService.actualizarCiudad(1L, actualizada);

        assertEquals("Ciudad Nueva", resultado.getNombre());
    }

    @Test
    void testActualizarCiudadNoExistente() {
        Ciudad actualizada = new Ciudad();
        actualizada.setNombre("Ciudad Falsa");

        when(ciudadRepository.findById(4L)).thenReturn(Optional.empty());

        assertThrows(CiudadNotFoundException.class, () -> {
            ciudadService.actualizarCiudad(4L, actualizada);
        });
    }
}
