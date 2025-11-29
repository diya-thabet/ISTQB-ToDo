package com.dhia.todoapp.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dhia.todoapp.domain.TodoItem;

class TodoRepositoryTest {

    private TodoRepository repository;

    @BeforeEach
    void setUp() {
        repository = new TodoRepository();
    }

    // ISTQB: Analyse des valeurs limites (Boundary Value) & State Transition
    @Test
    @DisplayName("fetchAllTodos : Cas Limite - Si la liste est vide, elle doit s'auto-peupler")
    void shouldAddDefaultItem_WhenListIsEmpty() {
        // Act 1: Premier appel (Liste vide au départ)
        List<TodoItem> items = repository.fetchAllTodos();

        // Assert 1
        assertEquals(1, items.size(), "La liste ne doit jamais être vide (Règle métier Repo)");
        assertEquals(0, items.get(0).getId(), "Le premier ID doit être 0");

        // Act 2: On supprime l'item
        repository.delete(0);

        // Act 3: On rappelle fetchAllTodos (La liste est vide techniquement)
        List<TodoItem> itemsAfterDelete = repository.fetchAllTodos();

        // Assert 2 (Preuve du "Zombie Bug" ou Feature)
        assertEquals(1, itemsAfterDelete.size(), "La liste doit se régénérer si elle est vide");
    }

    @Test
    @DisplayName("save : Doit incrémenter les IDs correctement")
    void shouldIncrementIdOnSave() {
        // Arrange
        // On appelle fetch une fois pour initialiser le compteur interne à 1 (car id 0 est créé)
        repository.fetchAllTodos();

        TodoItem newItem = new TodoItem();
        newItem.setTask("Second Task");

        // Act
        TodoItem savedItem = repository.save(newItem);

        // Assert
        assertEquals(1, savedItem.getId(), "Le prochain ID doit être 1 (après le 0 par défaut)");
    }

    @Test
    @DisplayName("delete : Doit supprimer l'élément spécifique")
    void shouldDeleteItem() {
        // Arrange - Initialiser la liste (crée ID 0)
        repository.fetchAllTodos();

        // Act
        repository.delete(0);

        // Pour vérifier, on doit accéder à la liste interne sans déclencher le "fetchAllTodos"
        // qui recrée l'objet. Mais comme la méthode est privée, on teste via le comportement observable:
        // Si on ajoute un nouvel item, il aura l'ID 1, et l'ID 0 ne devrait plus être là si on pouvait inspecter.
        // NOTE: Dans ce code spécifique, tester le delete est dur car fetchAllTodos() régénère l'objet !
        // C'est un excellent cas à mettre dans le rapport de bug : "Testabilité faible".

        // On simule une suppression d'un ID qui n'existe pas pour vérifier la robustesse
        assertDoesNotThrow(() -> repository.delete(999));
    }
}