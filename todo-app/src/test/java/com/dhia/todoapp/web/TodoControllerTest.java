package com.dhia.todoapp.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.dhia.todoapp.domain.TodoItem;
import com.dhia.todoapp.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simule des requêtes HTTP sans lancer le serveur

    @MockBean
    private TodoService todoService; // On isole le contrôleur du vrai service

    @Autowired
    private ObjectMapper objectMapper; // Pour convertir les objets Java en JSON

    @Test
    @DisplayName("GET /api/todoItems : Doit retourner la liste des tâches et un statut 200")
    void shouldFetchAllTodos() throws Exception {
        // Arrange
        TodoItem item1 = new TodoItem();
        item1.setId(1);
        item1.setTask("Task 1");

        TodoItem item2 = new TodoItem();
        item2.setId(2);
        item2.setTask("Task 2");

        List<TodoItem> todos = Arrays.asList(item1, item2);

        // On dit au MockService quoi répondre quand le contrôleur l'appellera
        when(todoService.fetchAllTodos()).thenReturn(todos);

        // Act & Assert
        mockMvc.perform(get("/api/todoItems"))
                .andExpect(status().isOk()) // Vérifie le code HTTP 200
                .andExpect(jsonPath("$.size()").value(2)) // Vérifie qu'il y a 2 éléments
                .andExpect(jsonPath("$[0].task").value("Task 1")); // Vérifie le contenu
    }

    @Test
    @DisplayName("POST /api/todoItems : Doit créer une tâche et retourner l'objet créé")
    void shouldCreateNewTodoItem() throws Exception {
        // Arrange
        TodoItem createdItem = new TodoItem();
        createdItem.setId(10);
        createdItem.setTask("Click to edit task name");
        createdItem.setIsDone(false);

        when(todoService.createNewTodoItem()).thenReturn(createdItem);

        // Act & Assert
        mockMvc.perform(post("/api/todoItems"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.task").value("Click to edit task name"));

        verify(todoService).createNewTodoItem(); // Vérifie que le service a bien été appelé
    }

    @Test
    @DisplayName("PUT /api/todoItems/{id} : Doit mettre à jour la tâche")
    void shouldUpdateTodoItem() throws Exception {
        // Arrange
        Integer id = 1;
        TodoItem updateData = new TodoItem();
        updateData.setTask("Updated Task");
        updateData.setIsDone(true);

        TodoItem updatedItem = new TodoItem();
        updatedItem.setId(id);
        updatedItem.setTask("Updated Task");
        updatedItem.setIsDone(true);

        // On configure le mock pour retourner l'objet mis à jour
        when(todoService.updateTodoItem(eq(id), any(TodoItem.class))).thenReturn(updatedItem);

        // Act & Assert
        mockMvc.perform(put("/api/todoItems/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateData))) // Envoie le JSON
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.task").value("Updated Task"))
                .andExpect(jsonPath("$.isDone").value(true));
    }

    @Test
    @DisplayName("DELETE /api/todoItems/{id} : Doit supprimer la tâche et retourner 'ok'")
    void shouldDeleteTodoItem() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/todoItems/{id}", 5))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("ok")); // Vérifie le corps de réponse string

        verify(todoService).deleteTodoItem(5);
    }
}