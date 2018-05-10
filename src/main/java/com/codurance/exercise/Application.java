package com.codurance.exercise;

import com.codurance.exercise.presentation.Command;
import com.codurance.exercise.presentation.CommandLineController;
import com.codurance.exercise.util.Constants;
import com.codurance.exercise.wrapper.ContentWrapper;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Console-based social networking application
 * @author Solange U. Gasengayire
 */
public class Application {
    private static final CommandLineController controller
            = new CommandLineController();

    private static Console console = System.console();

    /**
     * Application entry point
     * @param args application arguments
     */
    public static void main( String[] args ) {
        if (console == null) {
            System.err.println(Constants.CONSOLE_ERROR_MESSAGE);
            System.exit(1);
        }

        // print a startup message
        console.writer().println(Constants.STARTUP_MESSAGE);

        // prompt user for command
        String prompt = Constants.PROMPT_MESSAGE;
        String instruction = console.readLine(prompt);

        while (instruction != null && ! instruction.isEmpty()) {
            // execute user instruction
            executeCommand(instruction);
            // read next command
            instruction = console.readLine("> ");
        }
    }

    /**
     * Execute input command from the user
     * @param instruction user command (string)
     */
    private static void executeCommand(String instruction) {

        Command command = new Command(instruction);
        String operator = command.getOperator();
        String operand = command.getOperand();

        switch (command.getOperation()) {
            case POST:
                post(operator, operand);
                break;

            case READ:
                read(operator);
                break;

            case FOLLOW:
                follow(operator, operand);
                break;

            case WALL:
                displayWall(operator);
                break;

            case QUIT:
                console.writer().println(Constants.EXIT_MESSAGE);
                console.writer().flush();
                console.writer().close();
                System.exit(0);

            default:
                break;
        }
    }

    /**
     * Message post feature
     * @param user name of the user initiating the action
     * @param message message to be posted
     */
    private static void post(String user, String message) {
        controller.postMessage(user, message, LocalDateTime.now());
    }

    /**
     * Following feature
     * @param user name of the user initiating the action
     * @param subscription name of the user they're following
     */
    private static void follow(String user, String subscription) {
        controller.addFollowing(user, subscription, LocalDateTime.now());
    }

    /**
     * Display a given user's timeline
     * @param user name of the user initiating the action
     */
    private static void read(String user) {
        Map<LocalDateTime, ContentWrapper> userMessages = controller.getUserMessages(user);
        StringBuilder builder = new StringBuilder();
        for (ContentWrapper wrapper: userMessages.values()) {
            builder.append(wrapper.getFormattedContent());
            builder.append("\n");
        }
        console.writer().println(builder.toString());
        console.writer().flush();
    }

    /**
     * Display a given user's timeline, including
     * timelines of all their subscriptions
     * @param user name of the user initiating the action
     */
    private static void displayWall(String user) {
        Map<LocalDateTime, ContentWrapper> wall = controller.getAllMessages(user);
        StringBuilder builder = new StringBuilder();
        for (ContentWrapper wrapper: wall.values()) {
            builder.append(wrapper.toString());
            builder.append("\n");
        }
        console.writer().println(builder.toString());
        console.writer().flush();
    }
}
