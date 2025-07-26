package es.cic.curso25.proy008.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proy008.Model.Editorial;
import es.cic.curso25.proy008.Model.Libro;
import es.cic.curso25.proy008.Repository.LibroRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
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
        libro.setAnioDePublicacion(1932);
        

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
    void testCreateException() throws Exception {

        Libro libro = new Libro();
        libro.setNombreLibro("Bodas de sangre");
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1932);
        
        String libroJson = objectMapper.writeValueAsString(libro);

        mockMvc.perform(post("/libro")
                .contentType("application/json")
                .content(libroJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    void testDelete() throws Exception {

        Libro libro = new Libro();
        libro.setNombreLibro("Bodas de sangre");
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1932);

        //ObjectMapper objectMapper = new ObjectMapper();
        String libroJson = objectMapper.writeValueAsString(libro);

        mockMvc.perform(post("/libro")
                .contentType("application/json")
                .content(libroJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/libro/1"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/libro/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    void testUpdate() throws Exception {

        Libro libro = new Libro();
        libro.setNombreLibro("Bodas de sangre");
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1932);

        //ObjectMapper objectMapper = new ObjectMapper();
        String libroJson = objectMapper.writeValueAsString(libro);

        MvcResult result = mockMvc.perform(post("/libro")
                                .contentType("application/json")
                                .content(libroJson))
                                .andExpect(status().isOk())
                                .andReturn();

        String respuesta = result.getResponse().getContentAsString();
        Libro registroCreado= objectMapper.readValue(respuesta, Libro.class);

        registroCreado.setNombreLibro("Salvador Dalí");

        String libroActualizadoJson = objectMapper.writeValueAsString(registroCreado);
        
        mockMvc.perform(put("/libro/1")
                .contentType("application/json")
                .content (libroActualizadoJson))
                .andExpect(status().isOk())
                .andExpect(result2 -> {
                        String mensaje = result2.getResponse().getContentAsString();
                        Libro libroCreado = objectMapper.readValue(mensaje, Libro.class);
                        assertEquals(libroCreado.getNombreLibro(),"Salvador Dalí");

                        Optional<Libro> libroCreadoEnBase = libroRepository.findById(libroCreado.getId());
                        assertTrue(libroCreadoEnBase.isPresent());
                });
    }
    @Test
    void testGet() throws Exception{
        Libro libro = new Libro();
        libro.setNombreLibro("Bodas de sangre");
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1932);

        //ObjectMapper objectMapper = new ObjectMapper();
        String libroJson = objectMapper.writeValueAsString(libro);

        mockMvc.perform(post("/libro")
                                .contentType("application/json")
                                .content(libroJson));

                                
        
        
        Libro libro1 = new Libro();
        libro1.setNombreLibro("El contador de arena");
        libro1.setAutor("Arquimedes");
        libro1.setAnioDePublicacion((-240));


        //ObjectMapper objectMapper = new ObjectMapper();
        String libro1Json = objectMapper.writeValueAsString(libro1);

        mockMvc.perform(post("/libro")
                        .contentType("application/json")
                        .content(libro1Json));


        mockMvc.perform(get("/libro"))
                .andExpect(status().isOk())
                .andExpect(result ->{
                        String response = result.getResponse().getContentAsString();
                        Libro[] libros = objectMapper.readValue(response,Libro[].class);
                        
                        assertEquals(libros[0].getAnioDePublicacion(),1932);
                        assertEquals(libros[1].getAutor(), "Arquimedes");
                });
                                
    }
    @Test
    void testGetId() throws Exception{
        Libro libro = new Libro();
        libro.setNombreLibro("Bodas de sangre");
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1932);
        //convertimos el objeto de tipo libro en json con ObjectMapper
        String libroJson = objectMapper.writeValueAsString(libro);
        //simulamos la llamada HTTP y recogemos el id generado
        MvcResult mvcResult = mockMvc.perform(post("/libro")
            .contentType("application/json")
            .content(libroJson))
            .andExpect(status().isOk())
            .andReturn();
        Long idGenerado = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Libro.class).getId();
        //utilizamos ese id para probar la búsqueda
        //por si acaso (aunque es redundante) comprobamos si el id del objeto que nos devuelve es el mismo que el que hemos usado en la búsqueda
        mockMvc.perform(get("/libro/"+idGenerado))
            .andExpect(status().isOk())
            .andExpect( result ->{
                assertEquals(objectMapper.readValue(result.getResponse().getContentAsString(), Libro.class).getId(), idGenerado);
            });
    }
    @Test
    void testUpdateException() throws Exception {

        Libro libro = new Libro();
        libro.setNombreLibro("Bodas de sangre");
        libro.setAutor("Federico García Lorca");
        libro.setAnioDePublicacion(1932);
        
        String libroJson = objectMapper.writeValueAsString(libro);

        mockMvc.perform(put("/libro")
                .contentType("application/json")
                .content(libroJson))
                .andExpect(status().isNotFound());

    }
}
