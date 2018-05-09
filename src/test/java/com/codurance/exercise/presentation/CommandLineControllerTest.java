package com.codurance.exercise.presentation;

import com.codurance.exercise.wrapper.ContentWrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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

        ContentWrapper wrapper = (ContentWrapper) messagesArray[0];
        assertTrue(wrapper.getFormattedContent().contains("I love the weather today"));
        assertTrue(wrapper.getFormattedContent().contains("minutes ago)"));
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

        ContentWrapper wrapper = (ContentWrapper) messagesArray[0];
        assertEquals("Good game though.", wrapper.getContent());

        wrapper = (ContentWrapper) messagesArray[1];
        assertEquals("Damn! We lost!", wrapper.getContent());
    }

    @Test
    @DisplayName("Posting, following and displaying user wall")
    void shouldReturnAggregatedListOfMessages() {
        postAliceMessages();
        postBobMessages();
        postCharlieMessages();

        controller.addFollowing("Charlie", "Alice", LocalDateTime.now());
        Map<LocalDateTime, ContentWrapper> aggregatedMessages = controller.getAllMessages("Charlie");
        Object[] wall = aggregatedMessages.values().toArray();

        assertNotNull(aggregatedMessages);
        assertEquals(2, aggregatedMessages.size());

        ContentWrapper wrapper = (ContentWrapper) wall[0];
        assertEquals("Charlie", wrapper.getOwner());
        assertTrue(wrapper.getFormattedContent().contains("(2 seconds ago)"));

        wrapper = (ContentWrapper) wall[1];
        assertEquals("Alice", wrapper.getOwner());

        controller.addFollowing("Charlie", "Bob", LocalDateTime.now());
        aggregatedMessages = controller.getAllMessages("Charlie");
        wall = aggregatedMessages.values().toArray();

        assertEquals(4, aggregatedMessages.size());

        wrapper = (ContentWrapper) wall[0];
        assertEquals("Charlie", wrapper.getOwner());
        assertTrue(wrapper.getFormattedContent().contains("(2 seconds ago)"));

        wrapper = (ContentWrapper) wall[1];
        assertEquals("Bob", wrapper.getOwner());
        assertTrue(wrapper.getContent().contains("though."));

        wrapper = (ContentWrapper) wall[2];
        assertEquals("Bob", wrapper.getOwner());
        assertTrue(wrapper.getContent().contains("We lost!"));

        wrapper = (ContentWrapper) wall[3];
        assertEquals("Alice", wrapper.getOwner());
        assertTrue(wrapper.getFormattedContent().contains("(5 minutes ago)"));
    }

    /**
     * Prepare and post Alice's messages
     */
    private void postAliceMessages() {
        String user = "Alice";
        LocalDateTime timestamp = LocalDateTime.now().minusMinutes(5);
        String message = "I love the weather today";
        controller.postMessage(user, message, timestamp);
    }

    /**
     * Prepare and post Bob's messages
     */
    private void postBobMessages() {
        String user = "Bob";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timestamp = now.minusMinutes(2);
        String message = "Damn! We lost!";
        controller.postMessage(user, message, timestamp);

        timestamp = now.minusMinutes(1);
        message = "Good game though.";
        controller.postMessage(user, message, timestamp);
    }

    /**
     * Prepare and post Charlie's messages
     */
    private void postCharlieMessages() {
        String user = "Charlie";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timestamp = now.minusSeconds(2);
        String message = "I'm in New York today! Anyone want to have a coffee?";
        controller.postMessage(user, message, timestamp);
    }
}
