package com.codurance.exercise.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Command class unit tests
 * @author Solange U. Gasengayire
 */
@DisplayName("Command Unit Tests")
class CommandTest {

    @Test
    @DisplayName("Should interpret instruction as a read command")
    void shouldCreateReadCommand() {
        Command command = new Command("Alice");
        assertAll("Create a READ command",
                () -> assertNotNull(command),
                () -> assertEquals("Alice", command.getOperator()),
                () -> assertEquals("", command.getOperand()),
                () -> assertEquals(Command.Operation.READ, command.getOperation())
        );
    }

    @Test
    @DisplayName("Should interpret instruction as a post command")
    void shouldCreatePostCommand() {
        Command command = new Command("Alice -> I love the weather today!");
        assertAll("Create a POST command",
                () -> assertNotNull(command),
                () -> assertEquals("Alice", command.getOperator()),
                () -> assertEquals("I love the weather today!", command.getOperand()),
                () -> assertEquals(Command.Operation.POST, command.getOperation())
        );
    }

    @Test
    @DisplayName("Should interpret instruction as a follow command")
    void shouldCreateFollowCommand() {
        Command command = new Command("Alice follows Bob");
        assertAll("Create a FOLLOW command",
                () -> assertNotNull(command),
                () -> assertEquals("Alice", command.getOperator()),
                () -> assertEquals("Bob", command.getOperand()),
                () -> assertEquals(Command.Operation.FOLLOW, command.getOperation())
        );
    }

    @Test
    @DisplayName("Should interpret instruction as a wall command")
    void shouldCreateWallCommand() {
        Command command = new Command("Alice wall");
        assertAll("Create a WALL command",
                () -> assertNotNull(command),
                () -> assertEquals("Alice", command.getOperator()),
                () -> assertEquals("", command.getOperand()),
                () -> assertEquals(Command.Operation.WALL, command.getOperation())
        );
    }

}
