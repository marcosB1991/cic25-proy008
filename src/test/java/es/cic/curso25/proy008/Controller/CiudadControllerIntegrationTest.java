package es.cic.curso25.proy008.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.cic.curso25.proy008.Model.Biblioteca;
import es.cic.curso25.proy008.Model.Ciudad;
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

@SpringBootTest
@AutoConfigureMockMvc
public class CiudadControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearCiudad() throws Exception {
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre("Nueva Ciudad");

        String json = objectMapper.writeValueAsString(ciudad);

        mockMvc.perform(post("/ciudad")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Ciudad creada = objectMapper.readValue(result.getResponse().getContentAsString(), Ciudad.class);
                    assertTrue(creada.getId() > 0);
                });
    }

    @Test
    void testObtenerCiudadPorId() throws Exception {
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre("Ciudad Test");

        String json = objectMapper.writeValueAsString(ciudad);

        MvcResult result = mockMvc.perform(post("/ciudad")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        Long id = objectMapper.readValue(result.getResponse().getContentAsString(), Ciudad.class).getId();

        mockMvc.perform(get("/ciudad/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testActualizarCiudad() throws Exception {
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre("Ciudad Vieja");

        MvcResult result = mockMvc.perform(post("/ciudad")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(ciudad)))
                .andExpect(status().isOk())
                .andReturn();

        Long id = objectMapper.readValue(result.getResponse().getContentAsString(), Ciudad.class).getId();

        ciudad.setNombre("Ciudad Actualizada");

        mockMvc.perform(put("/ciudad/" + id)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(ciudad)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ciudad Actualizada"));
    }

    @Test
    void testEliminarCiudad() throws Exception {
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre("Ciudad A Eliminar");

        MvcResult result = mockMvc.perform(post("/ciudad")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(ciudad)))
                .andExpect(status().isOk())
                .andReturn();

        Long id = objectMapper.readValue(result.getResponse().getContentAsString(), Ciudad.class).getId();

        mockMvc.perform(delete("/ciudad/" + id))
                .andExpect(status().isOk());
    }

    @Test
    void testListarTodas() throws Exception {
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre("Ciudad Listado");

        mockMvc.perform(post("/ciudad")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(ciudad)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/ciudad"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    List<Ciudad> ciudades = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {}); // Aquí estamos diciendo: "esto es un List<Ciudad>" El objectMapper.readValue(...) necesita saber a qué tipo convertir el JSON, y gracias a new TypeReference<>() {} se da cuenta de que el tipo completo es List<Ciudad>.
                    assertTrue(ciudades.size() >= 1);
                });
    }

    @Test
    void testAsignarBibliotecaAlaCiudad() throws Exception {
        // Crear ciudad primero
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre("Ciudad Biblioteca");

        MvcResult ciudadResult = mockMvc.perform(post("/ciudad")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(ciudad)))
                .andExpect(status().isOk())
                .andReturn();

        Long idCiudad = objectMapper.readValue(ciudadResult.getResponse().getContentAsString(), Ciudad.class).getId();

        // Crear biblioteca para asignar
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setNombre("Biblioteca Asignada");
        biblioteca.setDireccion("Dirección Biblioteca");

        MvcResult bibResult = mockMvc.perform(post("/ciudad/" + idCiudad + "/biblioteca")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(biblioteca)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Biblioteca Asignada")) //$ Hace referencia a la raiz del json
                .andReturn();

        Biblioteca bibCreada = objectMapper.readValue(bibResult.getResponse().getContentAsString(), Biblioteca.class);
        assertEquals("Biblioteca Asignada", bibCreada.getNombre());
        assertNotNull(bibCreada.getId());
    }

    @Test
    void testObtenerBibliotecaDeCiudad() throws Exception {
        // Crear ciudad
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre("Ciudad Biblioteca Get");

        MvcResult ciudadResult = mockMvc.perform(post("/ciudad")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(ciudad)))
                .andExpect(status().isOk())
                .andReturn();

        Long idCiudad = objectMapper.readValue(ciudadResult.getResponse().getContentAsString(), Ciudad.class).getId();

        // Crear y asignar biblioteca
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setNombre("Biblioteca Get");
        biblioteca.setDireccion("Dirección Get");

        mockMvc.perform(post("/ciudad/" + idCiudad + "/biblioteca")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(biblioteca)))
                .andExpect(status().isOk());

        // Obtener biblioteca de ciudad
        mockMvc.perform(get("/ciudad/" + idCiudad + "/biblioteca"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Biblioteca Get"));
    }
}
