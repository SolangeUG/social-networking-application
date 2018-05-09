package com.codurance.exercise;

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
        String command = console.readLine(prompt);

        while (command != null && ! command.isEmpty()) {
            int result = executeCommand(command);

            if (result == 1) {
                // when exit command is requested
                console.writer().println(Constants.EXIT_MESSAGE);
                console.writer().flush();
                console.writer().close();
                System.exit(1);
            }
            // read next command
            command = console.readLine("> ");
        }
    }

    /**
     * Execute input command from the user
     * @param command user command (string)
     * @return result status code
     *         1 -> application exit code
     *         0 -> any-other-case code
     */
    private static int executeCommand(String command) {

        // assuming user input command is well-formed,
        // then we should have the following cases:

        // user is posting a message
        if (command.contains("->")) {
            int ndx = command.indexOf("->");
            int size = "->".length();
            String username = command.substring(0, ndx).trim();
            String message = command.substring(ndx + size).trim();
            post(username, message);
            return 0;
        }

        // user is following another user
        if (command.contains("follows")) {
            int ndx = command.indexOf("follows");
            int size = "follows".length();
            String username = command.substring(0, ndx).trim();
            String otherUser = command.substring(ndx + size).trim();
            follow(username, otherUser);
            return 0;
        }

        // user requests to display their aggregated wall
        if (command.contains("wall")) {
            int ndx = command.indexOf("wall");
            String username = command.substring(0, ndx).trim();
            displayWall(username);
            return 0;
        }

        // user exits the application
        if ("QUIT".equals(command)) {
            return 1;
        }

        // user requests to display their wall
        String username = command.trim();
        read(username);
        return 0;
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
