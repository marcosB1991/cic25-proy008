package es.cic.curso25.proy008.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;

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
        Libro libro1 = libroService.create(libro);
        libroService.delete(libro1.getId());
        Libro libroEliminado = libroService.getId(libro1.getId());
        assertNull(libroEliminado);
    }

    @Test
    void testGet() {
        List<Libro> lista = new ArrayList<>();
        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAñoDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        Libro libro1 = libroService.create(libro);
        lista.add(libro1);
        Libro libro2 = new Libro();
        libro2.setAutor("Federico García Lorca");
        libro2.setAñoDePublicacion(1928);
        libro2.setNombreLibro("Romancero Gitano");
        Libro libro3 = libroService.create(libro);
        lista.add(libro3);
        assertTrue(lista.size()==2);

    }

    @Test
    void testGetId() {
        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAñoDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        Libro libro1 = libroService.create(libro);
        Libro libro2 = libroService.getId(1l);
        assertTrue(libro2.getId()==libro1.getId());

    }

    @Test
    void testGetAutor() {
        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAñoDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        Libro libro1 = libroService.create(libro);
        List<Libro> lista = libroService.getAutor("Federico García Lorca");
        assertTrue(lista.size()>0,"La lista tiene elementos");
        assertTrue(lista.get(0).getAutor()==libro1.getAutor());

    }

    @Test
    void testGetAñoDePublicacion() {
        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAñoDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        Libro libro1 = libroService.create(libro);
        List<Libro> lista = libroService.getAñoDePublicacion(1928);
        assertTrue(lista.size()>0,"La lista tiene elementos");
        assertTrue(lista.get(0).getAñoDePublicacion()==libro1.getAñoDePublicacion());

    }

    @Test
    void testGetNombreLibro() {

        Libro libro = new Libro();
        libro.setAutor("Federico García Lorca");
        libro.setAñoDePublicacion(1928);
        libro.setNombreLibro("Romancero Gitano");
        Libro libro1 = libroService.create(libro);
        List<Libro> lista = libroService.getNombreLibro("Romancero Gitano");
        assertTrue(lista.size()>0,"La lista tiene elementos");
        assertTrue(lista.get(0).getNombreLibro()==libro1.getNombreLibro());

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
