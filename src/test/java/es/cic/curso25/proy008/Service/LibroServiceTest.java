package es.cic.curso25.proy008.Service;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.cic.curso25.proy008.Model.Libro;
@SpringBootTest
public class LibroServiceTest {
    @Autowired
    private LibroService libroService;


    @Test
    void testCreate() {
        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAñoDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        Libro libro1 = libroService.create(libro);
        assertTrue(libro1.getId()==1);
    }

    

    @Test
    void testDelete() {
        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAñoDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        libroService.create(libro).setId(1l);
        libroService.delete(1);
        assertNull(libro);



    }

    @Test
    void testGet() {

    }

    @Test
    void testGet1() {

    }

    @Test
    void testGetAutor() {

    }

    @Test
    void testGetAñoDePublicacion() {

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
       
        libroService.create(libro);

        libro.setAutor("Salvador Dalí");
        libroService.update(libro);

        Long id = new Long(1);
        Libro libroActualizado=libroService.getId(id);
        assertTrue(libroActualizado.getAutor().equals("Salvador Dalí"));

    }
}
