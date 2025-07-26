package es.cic.curso25.proy008.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import es.cic.curso25.proy008.Model.Editorial;


@SpringBootTest
public class EditorialServiceTest {
    @Autowired
    private EditorialService editorialService;


    @Test
    void testCreate() {
        Editorial editorial = new Editorial();
        editorial.setNombreEditorial("Alianza");
        editorial.setNumeroEdiciones(8);
        Editorial editorial1 = editorialService.create(editorial);
        assertTrue(editorial1.getId()>0);
    }


    @Test
    void testDelete() {
        Editorial editorial = new Editorial();
        editorial.setNombreEditorial("Alianza");
        editorial.setNumeroEdiciones(8);
        Editorial editorial1 = editorialService.create(editorial);
        editorialService.delete(editorial1.getId());
        Editorial editorialEliminada = editorialService.getId(editorial1.getId());
        assertNull(editorialEliminada);
    }

    @Test
    void testGet() {
        List<Editorial> editorials = new ArrayList<>();
        Editorial editorial = new Editorial();
        editorial.setNombreEditorial("Alianza");
        editorial.setNumeroEdiciones(8);
        Editorial editorial1 = editorialService.create(editorial);
        editorials.add(editorial1);
        Editorial editorial3 = new Editorial();
        editorial3.setNombreEditorial("Alianza");
        editorial3.setNumeroEdiciones(8);
        Editorial editorial2 = editorialService.create(editorial3);
        editorials.add(editorial2);
        assertTrue(editorials.size()==2);

    }

    @Test
    void testGetId() {
        Editorial editorial = new Editorial();
        editorial.setNombreEditorial("Alianza");
        editorial.setNumeroEdiciones(8);
        Editorial editorial1 = editorialService.create(editorial);
        editorialService.getId(1l);
        assertTrue(1<=editorial1.getId());
    }

    @Test
    void testGetNombreEditorial() {
        Editorial editorial = new Editorial();
        editorial.setNombreEditorial("Alianza");
        editorial.setNumeroEdiciones(8);
        editorialService.create(editorial);
        List<Editorial> lista = editorialService.getNombreEditorial("Alianza");
        assertTrue(lista.size()>0,"La lista tiene elementos");
        assertEquals(lista.get(0).getNombreEditorial(),"Alianza");

    }

    @Test
    void testGetNumeroEdiciones() {
        Editorial editorial = new Editorial();
        editorial.setNombreEditorial("Alianza");
        editorial.setNumeroEdiciones(8);
        Editorial editorial1 = editorialService.create(editorial);
        List<Editorial> lista = editorialService.getNumeroEdiciones(8);
        assertTrue(lista.size()>0,"La lista tiene elementos");
        assertTrue(lista.get(0).getNumeroEdiciones()==editorial1.getNumeroEdiciones());

    }

    @Test
    void testUpdate() {
        Editorial editorial = new Editorial();
        editorial.setNombreEditorial("Alianza");
        editorial.setNumeroEdiciones(8);
        editorialService.create(editorial);
        editorial.setNumeroEdiciones(9);
        editorialService.update(editorial);

        Editorial editorialActualizada=editorialService.getId(editorial.getId());
        assertEquals(editorialActualizada.getNumeroEdiciones(), 9);

    }

}
