package es.cic.curso25.proy008.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.cic.curso25.proy008.Model.Biblioteca;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest //Simula que levanta la aplicacion
@AutoConfigureMockMvc //herramienta para simular peticiones HTTP (GET, POST, etc.)
public class BibliotecaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearBiblioteca() throws Exception {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setNombre("Nueva Biblioteca");
        biblioteca.setDireccion("Calle Falsa 123");

        String json = objectMapper.writeValueAsString(biblioteca);

        mockMvc.perform(post("/bibliotecas")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Biblioteca creada = objectMapper.readValue(result.getResponse().getContentAsString(), Biblioteca.class);
                    assertTrue(creada.getId() > 0);
                });
    }

    @Test
    void testObtenerBibliotecaPorId() throws Exception {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setNombre("Central");
        biblioteca.setDireccion("Calle 1");

        String json = objectMapper.writeValueAsString(biblioteca);

        MvcResult result = mockMvc.perform(post("/bibliotecas")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        Long id = objectMapper.readValue(result.getResponse().getContentAsString(), Biblioteca.class).getId();

        mockMvc.perform(get("/bibliotecas/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testActualizarBiblioteca() throws Exception {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setNombre("Vieja");
        biblioteca.setDireccion("Calle 2");

        MvcResult result = mockMvc.perform(post("/bibliotecas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(biblioteca)))
                .andExpect(status().isOk())
                .andReturn();

        Long id = objectMapper.readValue(result.getResponse().getContentAsString(), Biblioteca.class).getId();

        biblioteca.setNombre("Actualizada");
        biblioteca.setDireccion("Nueva dirección");

        mockMvc.perform(put("/bibliotecas/" + id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(biblioteca)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Actualizada"));
    }

    @Test
    void testEliminarBiblioteca() throws Exception {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setNombre("A Eliminar");
        biblioteca.setDireccion("Calle Borrar");

        MvcResult result = mockMvc.perform(post("/bibliotecas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(biblioteca)))
                .andExpect(status().isOk())
                .andReturn();

        Long id = objectMapper.readValue(result.getResponse().getContentAsString(), Biblioteca.class).getId();

        mockMvc.perform(delete("/bibliotecas/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void testListarTodas() throws Exception {
        // Asegura que haya al menos una
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setNombre("Listado");
        biblioteca.setDireccion("Calle Listado");

        mockMvc.perform(post("/bibliotecas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(biblioteca)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/bibliotecas"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    List<Biblioteca> bibliotecas = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {});
                    assertTrue(bibliotecas.size() >= 1);
                });
    }
}
