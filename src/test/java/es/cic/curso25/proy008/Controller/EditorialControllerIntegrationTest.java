package es.cic.curso25.proy008.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proy008.Model.Editorial;
import es.cic.curso25.proy008.Model.Libro;
import es.cic.curso25.proy008.Repository.EditorialRepository;
import es.cic.curso25.proy008.Repository.LibroRepository;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EditorialControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EditorialRepository editorialRepository;
    @Autowired 
    private ObjectMapper objectMapper;
    @Test
    void testCreate() throws Exception {

        Editorial editorial = new Editorial();
        editorial.setNombreEditorial("Alianza");
        editorial.setNumeroEdiciones(7);

        String editorialJson = objectMapper.writeValueAsString(editorial);

        mockMvc.perform(post("/editorial")
                        .contentType("application/json")
                        .content(editorialJson))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(result ->{
                            String mensaje = result.getResponse().getContentAsString();
                            Editorial editorialCreada = objectMapper.readValue(mensaje, Editorial.class);
                            assertTrue(editorialCreada.getId()>0, "La editorial se ha podido crear");

                            Optional<Editorial> editorialCreada1 = editorialRepository.findById(editorialCreada.getId());
                            assertTrue(editorialCreada1.isPresent());
                });
                                     

    }
    @Test
    void testDelete() throws Exception {

        Editorial editorial = new Editorial();
        editorial.setNombreEditorial("Alianza");
        editorial.setNumeroEdiciones(7);

        String editorialJson = objectMapper.writeValueAsString(editorial);
       
        mockMvc.perform(post("/editorial")
                .contentType("application/json")
                .content(editorialJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/editorial/1"))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/editorial/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    void testUpdate() throws Exception {

        Editorial editorial = new Editorial();
        editorial.setNombreEditorial("Alianza");
        editorial.setNumeroEdiciones(7);

        String editorialJson = objectMapper.writeValueAsString(editorial);

        MvcResult result = mockMvc.perform(post("/editorial")
                                .contentType("application/json")
                                .content(editorialJson))
                                .andExpect(status().isOk())
                                .andReturn();

        String respuesta = result.getResponse().getContentAsString();
        Editorial registroCreado= objectMapper.readValue(respuesta, Editorial.class);

        registroCreado.setNumeroEdiciones(8);

        String editorialActualizadaJson = objectMapper.writeValueAsString(registroCreado);
        
        mockMvc.perform(put("/editorial/1")
                .contentType("application/json")
                .content (editorialActualizadaJson))
                .andExpect(status().isOk())
                .andExpect(result2 -> {
                        String mensaje = result2.getResponse().getContentAsString();
                        Editorial editorialActualizada = objectMapper.readValue(mensaje, Editorial.class);
                        assertEquals(editorialActualizada.getNumeroEdiciones(),8);

                        Optional<Editorial> comprobacionEnBase= editorialRepository.findById(editorialActualizada.getId());
                        assertTrue(comprobacionEnBase.isPresent());
                });
    }
    @Test
    void testGet() throws Exception{
        Editorial editorial = new Editorial();
        editorial.setNombreEditorial("Alianza");
        editorial.setNumeroEdiciones(7);

        String editorialJson = objectMapper.writeValueAsString(editorial);

        mockMvc.perform(post("/editorial")
                                .contentType("application/json")
                                .content(editorialJson));

                                
        
        
        Editorial editorial1 = new Editorial();
        editorial1.setNombreEditorial("Alianza");
        editorial1.setNumeroEdiciones(8);

        String editorialJson1 = objectMapper.writeValueAsString(editorial1);

        mockMvc.perform(post("/editorial")
                                .contentType("application/json")
                                .content(editorialJson1));


        mockMvc.perform(get("/editorial"))
                .andExpect(status().isOk())
                .andExpect(result ->{
                        String response = result.getResponse().getContentAsString();
                        Editorial[] editorials = objectMapper.readValue(response,Editorial[].class);
                        
                        assertEquals(editorials[0].getNumeroEdiciones(),7);
                        assertEquals(editorials[1].getNumeroEdiciones(), 8);
                });
                                
    }
    @Test
    void testGetId() throws Exception{
    Editorial editorial = new Editorial();
        editorial.setNombreEditorial("Alianza");
        editorial.setNumeroEdiciones(7);

        String editorialJson = objectMapper.writeValueAsString(editorial);

        MvcResult mvcResult = mockMvc.perform(post("/editorial")
                                .contentType("application/json")
                                .content(editorialJson))
                                .andExpect(status().isOk())
                                .andReturn();
        Long idGenerado = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Editorial.class).getId();
        //utilizamos ese id para probar la búsqueda
        //por si acaso (aunque es redundante) comprobamos si el id del objeto que nos devuelve es el mismo que el que hemos usado en la búsqueda
        mockMvc.perform(get("/editorial/"+idGenerado))
            .andExpect(status().isOk())
            .andExpect( result ->{
                assertEquals(objectMapper.readValue(result.getResponse().getContentAsString(), Editorial.class).getId(), idGenerado);
            });
    }
}


