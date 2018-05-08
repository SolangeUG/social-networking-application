package com.codurance.exercise.presentation;

import com.codurance.exercise.wrapper.ContentWrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Command-Line Controller unit tests
 * @author Solange U. Gasengayire
 */
@DisplayName("Command Line Controller Unit Tests")
class CommandLineControllerTest {

    private CommandLineController controller =
            new CommandLineController();

    @Test
    @DisplayName("Posting and reading a message")
    void shouldReturnUserFirstMessage() {
        postAliceMessages();

        Map<LocalDateTime, ContentWrapper> messages = controller.getUserMessages("Alice");
        Object[] messagesArray = messages.values().toArray();

        assertNotNull(messages);
        assertEquals(1, messages.size());
        assertNotNull(messagesArray);
        assertEquals(1, messagesArray.length);
        assertTrue(((String) messagesArray[0]).contains("I love the weather today"));
        assertTrue(((String) messagesArray[0]).contains("minutes ago)"));
    }

    @Test
    @DisplayName("Posting and reading messages in chronological order")
    void shouldReturnedListOfMessages() {
        postBobMessages();

        Map<LocalDateTime, ContentWrapper> messages = controller.getUserMessages("Bob");
        Object[] messagesArray = messages.values().toArray();

        assertNotNull(messages);
        assertEquals(2, messages.size());
        assertEquals(2, messagesArray.length);
        assertTrue(((String) messagesArray[0]).contains("Good game though."));
        assertTrue(((String) messagesArray[1]).contains("Damn! We lost!"));
    }

    @Test
    @DisplayName("Posting, following and displaying user wall")
    void shouldReturnAggregatedListOfMessages() {
        postCharlieMessages();

        controller.addFollowing("Charlie", "Alice", LocalDateTime.now());
        Map<LocalDateTime, ContentWrapper> aggregatedMessages = controller.getAllMessages("Charlie");
        Object[] wall = aggregatedMessages.values().toArray();

        assertNotNull(aggregatedMessages);
        assertEquals(2, aggregatedMessages.size());
        assertTrue(((String) wall[0]).contains("Charlie"));
        assertTrue(((String) wall[1]).contains("Alice"));

        controller.addFollowing("Charlie", "Bob", LocalDateTime.now());
        aggregatedMessages = controller.getAllMessages("Charlie");
        wall = aggregatedMessages.values().toArray();

        assertEquals(4, aggregatedMessages.size());
        assertTrue(((String) wall[0]).contains("Charlie"));
        assertTrue(((String) wall[1]).contains("Bob"));
        assertTrue(((String) wall[2]).contains("Bob"));
        assertTrue(((String) wall[3]).contains("Alice"));
    }

    /**
     * Prepare and post Alice's messages
     */
    private void postAliceMessages() {
        String user = "Alice";
        LocalDateTime timestamp = LocalDateTime.now().minus(5, ChronoUnit.MINUTES);
        String message = "I love the weather today";
        controller.postMessage(user, message, timestamp);
    }

    /**
     * Prepare and post Bob's messages
     */
    private void postBobMessages() {
        String user = "Bob";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timestamp = now.minus(2, ChronoUnit.MINUTES);
        String message = "Damn! We Lost!";
        controller.postMessage(user, message, timestamp);

        timestamp = now.minus(1, ChronoUnit.MINUTES);
        message = "Good game though.";
        controller.postMessage(user, message, timestamp);
    }

    /**
     * Prepare and post Charlie's messages
     */
    private void postCharlieMessages() {
        String user = "Charlie";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timestamp = now.minus(2, ChronoUnit.SECONDS);
        String message = "I'm in New York today! Anyone want to have a coffee?";
        controller.postMessage(user, message, timestamp);
    }
}
