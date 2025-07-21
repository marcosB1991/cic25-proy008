package es.cic.curso25.proy008.Controller;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso25.proy008.Model.Libro;

@SpringBootTest
public class LibroControllerTest {

    @Autowired
    private LibroController libroController;
    @Test
    void testCreate() {
        Libro libro = new Libro();
        Long id= new Long(1);
        libro.setId(id);
        libro.setAutor("Federico García Lorca");
        libro.setAñoDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        Libro libro1 = libroController.create(libro);
        assertInstanceOf(Libro.class, libro1);
    }

    @Test
    void testDelete() {

        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAñoDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        Long id = new Long(1);
        libroController.create(libro).setId(id);
        libroController.delete(1);
        assertNull(libro);


        
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
       
        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAñoDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
       
        libroController.create(libro);

        libro.setAutor("Salvador Dalí");
        libroController.update(libro);

        Long id = new Long(1);
        Libro libroActualizado=libroController.getId(id);
        assertTrue(libroActualizado.getAutor().equals("Salvador Dalí"));

    }
}
