package com.dhia.todoapp.integration;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.dhia.todoapp.domain.TodoItem;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest // Lance toute l'application Spring (Vrai Service, Vrai Repo)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Force l'ordre des tests pour simuler un scénario
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS) // Nettoie le contexte après
class TodoFullStackIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // SCÉNARIO : Un utilisateur arrive sur l'application vide

    @Test
    @Order(1)
    void step1_shouldStartWithDefaultItem() throws Exception {
        // Au démarrage, le Repo crée automatiquement un item par défaut (Logique "Zombie")
        mockMvc.perform(get("/api/todoItems"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].task", is("Click to edit task name")));
    }

    @Test
    @Order(2)
    void step2_shouldCreateNewItem() throws Exception {
        // L'utilisateur clique sur "Add New"
        mockMvc.perform(post("/api/todoItems"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.task", is("Click to edit task name")))
                .andExpect(jsonPath("$.isDone", is(false)));

        // Vérification : On doit maintenant avoir 2 items
        mockMvc.perform(get("/api/todoItems"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @Order(3)
    void step3_shouldUpdateItem() throws Exception {
        // L'utilisateur modifie le PREMIER item (ID 0)
        TodoItem updateData = new TodoItem();
        updateData.setTask("Acheter du pain");
        updateData.setIsDone(true);

        mockMvc.perform(put("/api/todoItems/{id}", 0)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.task", is("Acheter du pain")))
                .andExpect(jsonPath("$.isDone", is(true)));
    }

    @Test
    @Order(4)
    void step4_shouldDeleteItem() throws Exception {
        // L'utilisateur supprime l'item qu'il vient de modifier (ID 0)
        mockMvc.perform(delete("/api/todoItems/{id}", 0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("ok")));

        // Vérification : Il ne doit rester que l'item créé à l'étape 2
        mockMvc.perform(get("/api/todoItems"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1))); // L'ID 1 (créé au step 2)
    }

    @Test
    @Order(5)
    void step5_demonstrateZombieBug() throws Exception {
        // C'est ici qu'on prouve le bug "Zombie" dans un rapport de test automatisé !

        // 1. On supprime le DERNIER item restant (ID 1)
        mockMvc.perform(delete("/api/todoItems/{id}", 1))
                .andExpect(status().isOk());

        // 2. La liste est maintenant techniquement vide.
        // 3. MAIS, si on la redemande, le backend va recréer un item par défaut.

        mockMvc.perform(get("/api/todoItems"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))) // On s'attendait à 0, mais c'est 1 !
                .andExpect(jsonPath("$[0].task", is("Click to edit task name")));

        // Ce test PASSANT prouve que le bug est reproductible et "documenté" par le code.
    }
}