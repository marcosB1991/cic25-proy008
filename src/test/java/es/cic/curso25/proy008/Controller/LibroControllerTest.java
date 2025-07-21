package es.cic.curso25.proy008.Controller;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.cic.curso25.proy008.Model.Libro;

public class LibroControllerTest {

    @Autowired
    private LibroController libroController;
    @Test
    void testCreate() {
        private Libro libro;
        private Long id;
        libro.setId(id);
        libro.setAutor("Federico García Lorca");
        libro.setAñoDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        libroController.create(libro);
        assertInstanceOf(Libro.class, libro);
    }

    @Test
    void testDelete() {

    }

    @Test
    void testGet() {

    }

    @Test
    void testGetAutor() {

    }

    @Test
    void testGetAñoDePublicacion() {

    }

    @Test
    void testGetId() {

    }

    @Test
    void testGetNombreLibro() {

    }

    @Test
    void testUpdate() {

    }
}
