package es.cic.curso25.proy008.Controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
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
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        Libro libro1 = libroController.create(libro);
        assertTrue(libro1.getId()>0);
    }

    @Test
    void testDelete() {

        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        Libro libro1 = libroController.create(libro);
        Long id = libro1.getId();
        libroController.delete(id);
        Libro libroEliminado = libroController.getId(id);
        assertNull(libroEliminado);
        
    }

    @Test
    void testGet() {
        List<Libro> lista = new ArrayList<>();
        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        Libro libro1 = libroController.create(libro);
        lista.add(libro1);
        Libro libro2 = new Libro();
        libro2.setAutor("Federico García Lorca");
        libro2.setAnioDePublicacion(1928);
        libro2.setNombreLibro("Romancero Gitano");
        Libro libro3 = libroController.create(libro);
        lista.add(libro3);
        assertTrue(lista.size()==2);

    }

    @Test
    void testGetAutor() {
        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        libroController.create(libro);
        List<Libro> lista = libroController.getAutor("Federico García Lorca");
        assertTrue(lista.size()>0,"La lista tiene elementos");
        assertEquals(lista.get(0).getAutor(), "Federico García Lorca");

    }

    @Test
    void testGetAnioDePublicacion() {
        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        Libro libro1 = libroController.create(libro);
        List<Libro> lista = libroController.getAnioDePublicacion(1928);
        assertTrue(lista.size()>0,"La lista tiene elementos");
        assertTrue(lista.get(0).getAnioDePublicacion()==libro1.getAnioDePublicacion());

    }

    @Test
    void testGetId() {
        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        Libro libro1 = libroController.create(libro);
        libroController.getId(1l);
        assertTrue(1<=libro1.getId());

    }

    @Test
    void testGetNombreLibro() {
         Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        Libro libro1 = libroController.create(libro);
        List<Libro> lista = libroController.getNombreLibro("Romancero Gitano");
        assertTrue(lista.size()>0,"La lista tiene elementos");
        assertTrue(lista.get(0).getNombreLibro()==libro1.getNombreLibro());

    }

    @Test
    void testUpdate() {
       
        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
       
        libroController.create(libro);

        libro.setAutor("Salvador Dalí");
        libroController.update(libro);

        Libro libroActualizado=libroController.getId(libro.getId());
        assertTrue(libroActualizado.getAutor().equals("Salvador Dalí"));

    }
}
