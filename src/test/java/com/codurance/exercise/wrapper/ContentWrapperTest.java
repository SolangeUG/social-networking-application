package com.codurance.exercise.wrapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Content wrapper class unit tests
 * @author Solange U. Gasengayire
 */
@DisplayName("Content wrapper class unit tests")
class ContentWrapperTest {

    @Test
    @DisplayName("Format wrappper content")
    void shouldReturnWellFormattedContent() {
        LocalDateTime now = LocalDateTime.now();
        ContentWrapper wrapper = new ContentWrapper(
                "Alice",
                "I love the weather today",
                now.minusDays(4)
        );

        String content = wrapper.getFormattedContent();
        assertNotNull(content);
        assertFalse(content.isEmpty());
        assertTrue(content.contains("4 days ago"));
    }

    @Test
    @DisplayName("Change locale and format wrapper content accodingly")
    void shouldReturnLocalizedContent() {
        ContentWrapper wrapper = new ContentWrapper(
                "Jeanne",
                "J'adore la météo aujourd'hui",
                LocalDateTime.now().minusDays(1),
                Locale.FRANCE
        );

        String content = wrapper.getFormattedContent();
        assertNotNull(content);
        assertFalse(content.isEmpty());
        assertTrue(content.contains("il y a 1 jour"));

        content = wrapper.toString();
        assertTrue(content.contains("Jeanne"));
    }

    @Test
    @DisplayName("Change locale and time difference and format wrapper content accodingly")
    void shouldReturnLocalizedContentInHours() {
        Locale spanish = new Locale("es", "ES");
        ContentWrapper wrapper = new ContentWrapper(
                "Pedro",
                "Me gusta hablar contigo",
                LocalDateTime.now().minusMinutes(20),
                spanish
        );

        String content = wrapper.getFormattedContent();
        assertNotNull(content);
        assertFalse(content.isEmpty());
        assertTrue(content.contains("hace 20 minutos"));

        content = wrapper.toString();
        assertTrue(content.contains("Pedro"));
    }
}
