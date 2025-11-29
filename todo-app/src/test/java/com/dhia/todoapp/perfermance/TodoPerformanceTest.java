package com.dhia.todoapp.perfermance;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dhia.todoapp.service.TodoService;

@SpringBootTest
@Tag("performance") // Permet d'exclure ce test des builds rapides si besoin
class TodoPerformanceTest {

    @Autowired
    private TodoService todoService;

    @Test
    @DisplayName("Performance: Doit pouvoir créer 1000 tâches en moins de 2 secondes")
    void shouldHandleLoad_Create1000Items() {
        // Arrange
        int numberOfItems = 1000;
        long startTime = System.currentTimeMillis();

        // Act: On bombarde le service
        for (int i = 0; i < numberOfItems; i++) {
            todoService.createNewTodoItem();
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Log pour le rapport
        System.out.println("Temps pour créer " + numberOfItems + " items : " + duration + "ms");

        // Assert: Critère d'acceptation de performance
        assertTrue(duration < 2000, "La création de masse est trop lente ! (" + duration + "ms)");
    }
}