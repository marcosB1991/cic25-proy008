package es.cic.curso25.proy008.uc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.cic.curso25.proy008.Model.Editorial;
import es.cic.curso25.proy008.Model.Libro;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EditorialDelLibro {

    @Autowired
    private MockMvc mockMvc;
    @Autowired 
    private ObjectMapper objectMapper;
    

    @Test
    void testPatrocinarElLibro() throws Exception{

        Libro libro = new Libro();
        libro.setNombreLibro("Bodas de sangre");
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1932);

        Editorial editorial = new Editorial();
        editorial.setNombreEditorial("Alianza Editorial");
        editorial.setNumeroEdiciones(7);

        libro.setEditorial(editorial);
        editorial.setLibro(libro);
        
        String libroACrearJson = objectMapper.writeValueAsString(libro);

        mockMvc.perform(post("/libro")
                .contentType("application/json")
                .content(libroACrearJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(libroCreado -> {
                                assertNotNull(objectMapper.readValue(
                                libroCreado.getResponse().getContentAsString(), Libro.class), 
                                "La editorial compro el libro");
            });
    }

}
