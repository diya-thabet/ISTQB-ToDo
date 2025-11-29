package com.dhia.todoapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dhia.todoapp.domain.TodoItem;
import com.dhia.todoapp.repository.TodoRepository;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepo; // On "Mock" le repository pour isoler le Service

    @InjectMocks
    private TodoService todoService; // On injecte le Mock dans le Service

    // ISTQB: Test de régression (Happy Path)
    @Test
    @DisplayName("fetchAllTodos : Doit retourner la liste fournie par le repository")
    void shouldReturnAllTodos() {
        // Arrange
        List<TodoItem> mockList = new ArrayList<>();
        mockList.add(new TodoItem());
        when(todoRepo.fetchAllTodos()).thenReturn(mockList);

        // Act
        List<TodoItem> result = todoService.fetchAllTodos();

        // Assert
        assertEquals(1, result.size(), "Le service doit relayer la liste exacte du repo");
        verify(todoRepo).fetchAllTodos(); // Vérifie que la méthode a bien été appelée
    }

    // ISTQB: White Box Testing - Couverture de la branche "If Present" (Vrai)
    @Test
    @DisplayName("updateTodoItem : Doit mettre à jour si l'ID existe (Branche VRAI)")
    void shouldUpdateTodoItem_WhenIdExists() {
        // Arrange
        Integer targetId = 1;
        TodoItem existingItem = new TodoItem();
        existingItem.setId(targetId);
        existingItem.setTask("Old Task");
        existingItem.setIsDone(false);

        // Simulation de la liste retournée par le repo
        List<TodoItem> repoList = new ArrayList<>();
        repoList.add(existingItem);
        when(todoRepo.fetchAllTodos()).thenReturn(repoList);

        TodoItem updateData = new TodoItem();
        updateData.setTask("New Task");
        updateData.setIsDone(true);

        // Act
        TodoItem result = todoService.updateTodoItem(targetId, updateData);

        // Assert
        assertNotNull(result);
        assertEquals("New Task", result.getTask()); // Vérifie la MAJ
        assertTrue(result.getIsDone()); // Vérifie la MAJ
    }

    // ISTQB: White Box Testing - Couverture de la branche "Else / Not Present" (Faux)
    // C'est ce test qui garantit les 100% de couverture de décision.
    @Test
    @DisplayName("updateTodoItem : Doit retourner NULL si l'ID n'existe pas (Branche FAUX)")
    void shouldReturnNull_WhenIdDoesNotExist() {
        // Arrange
        Integer wrongId = 99;
        when(todoRepo.fetchAllTodos()).thenReturn(new ArrayList<>()); // Liste vide

        TodoItem updateData = new TodoItem();

        // Act
        TodoItem result = todoService.updateTodoItem(wrongId, updateData);

        // Assert
        assertNull(result, "Doit retourner null si l'item n'est pas trouvé");
    }

    // ISTQB: Test de création et validation des valeurs par défaut
    @Test
    @DisplayName("createNewTodoItem : Doit créer un item avec les valeurs par défaut")
    void shouldCreateNewTodoItem() {
        // Arrange
        when(todoRepo.save(any(TodoItem.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        TodoItem created = todoService.createNewTodoItem();

        // Assert
        assertFalse(created.getIsDone(), "Une nouvelle tâche doit être à FALSE");
        assertEquals("Click to edit task name", created.getTask(), "Doit avoir le nom par défaut");
        verify(todoRepo).save(any(TodoItem.class));
    }
}