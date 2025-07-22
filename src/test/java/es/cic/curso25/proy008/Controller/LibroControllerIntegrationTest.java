package es.cic.curso25.proy008.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proy008.Controller.LibroNoCreadoException;
import es.cic.curso25.proy008.Model.Libro;
import es.cic.curso25.proy008.Repository.LibroRepository;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class LibroControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired 
    private ObjectMapper objectMapper;
    @Test
    void testCreate() throws Exception {

        Libro libro = new Libro();
        libro.setNombreLibro("Bodas de sangre");
        libro.setAutor("Federico García Lorca");
        libro.setAñoDePublicacion(1932);
        

        // ObjectMapper objectMapper = new ObjectMapper();
        String libroJson = objectMapper.writeValueAsString(libro);

        mockMvc.perform(post("/libro")
                .contentType("application/json")
                .content(libroJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                                String mensaje = result.getResponse().getContentAsString();
                                Libro libroCreado = objectMapper.readValue(mensaje, Libro.class);
                                assertTrue(libroCreado.getId() > 0, "El valor debe ser mayor que 0");

                                Optional<Libro> libroCreadoEnBase = libroRepository.findById(libroCreado.getId());
                                assertTrue(libroCreadoEnBase.isPresent());
                });

            }
     @Test
    void testDelete() throws Exception {

        Libro libro = new Libro();
        libro.setNombreLibro("Bodas de sangre");
        libro.setAutor("Federico García Lorca");
        libro.setAñoDePublicacion(1932);

        ObjectMapper objectMapper = new ObjectMapper();
        String libroJson = objectMapper.writeValueAsString(libro);

        mockMvc.perform(post("/libro")
                .contentType("application/json")
                .content(libroJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/libro/1"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/libro/1"))
                .andExpect(status().isOk())
                .andReturn();


    }
}
