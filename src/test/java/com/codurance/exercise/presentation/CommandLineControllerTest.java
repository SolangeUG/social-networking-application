package com.codurance.exercise.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    void shouldReturnUserFirstMessage() throws Exception {
        controller.executeInstruction("Alice -> I love the weather today");

        Thread.sleep(4000);
        String aliceMessage = controller.executeInstruction("Alice");

        assertAll("Post and read a user's message",
                () -> assertNotNull(aliceMessage),
                () -> assertNotEquals("", aliceMessage),
                () -> assertTrue(aliceMessage.contains("I love the weather today")),
                () -> assertTrue(aliceMessage.contains("4 seconds ago"))
        );
    }

    @Test
    @DisplayName("Posting and reading messages in chronological order")
    void shouldReturnListOfMessages() throws Exception {
        controller.executeInstruction("Bob -> Damn! We lost!");
        Thread.sleep(4000);
        controller.executeInstruction("Bob -> Good game though.");

        String[] messages = controller.executeInstruction("Bob").split("\\R");
        assertAll("Get user's messages ordered from newest to oldest",
                () -> assertTrue(messages[0].contains("Good game though.")),
                () -> assertTrue(messages[0].contains("1 second ago")),
                () -> assertTrue(messages[1].contains("Damn! We lost!")),
                () -> assertTrue(messages[1].contains("seconds ago"))
        );
    }

    @Test
    @DisplayName("Posting, following and displaying user wall")
    void shouldReturnAggregatedListOfMessages() throws Exception {
        controller.executeInstruction("Alice -> I love the weather today");
        Thread.sleep(2000);
        controller.executeInstruction("Bob -> Damn! We lost!");
        controller.executeInstruction("Bob -> Good game though.");
        Thread.sleep(2000);
        controller.executeInstruction("Charlie -> I'm in New York today! Anyone want to have a coffee?");

        controller.executeInstruction("Charlie follows Alice");
        String[] messages = controller.executeInstruction("Charlie wall").split("\\R");
        assertAll("A user's aggregated messages should be ordered from newest to oldest",
                () -> assertTrue(messages[0].contains("Charlie")),
                () -> assertTrue(messages[0].contains("I'm in New York today! Anyone want to have a coffee?")),
                () -> assertTrue(messages[1].contains("Alice")),
                () -> assertTrue(messages[1].contains("I love the weather today"))
        );

        controller.executeInstruction("Charlie follows Bob");
        String[] wall = controller.executeInstruction("Charlie wall").split("\\R");
        assertAll("A user's aggregated messages should be ordered from newest to oldest",
                () -> assertTrue(wall[0].contains("Charlie")),
                () -> assertTrue(wall[0].contains("in New York today!")),
                () -> assertTrue(wall[1].contains("Bob")),
                () -> assertTrue(wall[1].contains("Good game though.")),
                () -> assertTrue(wall[2].contains("Bob")),
                () -> assertTrue(wall[2].contains("Damn! We lost!")),
                () -> assertTrue(wall[3].contains("Alice")),
                () -> assertTrue(wall[3].contains("I love the weather today"))
        );
    }
}
