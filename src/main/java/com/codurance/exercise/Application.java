package com.codurance.exercise;

import com.codurance.exercise.presentation.CommandLineController;
import com.codurance.exercise.wrapper.ContentWrapper;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Console-based social networking application
 * @author Solange U. Gasengayire
 */
public class Application {
    private static final CommandLineController controller
            = new CommandLineController();

    private static Console console = System.console();

    private static ResourceBundle bundle = ResourceBundle.getBundle(
            "messages",
            Locale.getDefault());

    /**
     * Application entry point
     * @param args application arguments
     */
    public static void main( String[] args ) {
        if (console == null) {
            System.err.println(bundle.getString("application.messages.error.console"));
            System.exit(1);
        }

        // print a startup message
        console.writer().println(bundle.getString("application.messages.startup"));

        // prompt user for command
        String prompt = bundle.getString("application.messages.prompt");
        String command = console.readLine(prompt);

        while (command != null && ! command.isEmpty()) {
            // retrieve and execute input command and parameters from the console
            String[] parameters = command.split("\\s+");
            int result = executeCommand(parameters);

            if (result == 1) {
                // when exit command is requested
                console.writer().println(bundle.getString("application.messages.exit"));
                console.flush();
                System.exit(1);
            }
            // read next command
            command = console.readLine("> ");
        }
    }

    /**
     * Execute input command from the user
     * @param parameters command parameters
     * @return result status code
     *         1 -> application exit code
     *         0 -> any-other-case code
     */
    private static int executeCommand(String[] parameters) {
        if (parameters != null) {

            // assuming user input command is well-formed,
            // then parameters should take one of these forms:
            //      (1) parameters[0] => user name
            //      (2) parameters[0] => QUIT
            //      (3) parameters[0] => user name | parameters[1] => wall
            //      (4) parameters[0] => user name | parameters[1] => ->        | parameters[2] => message
            //      (5) parameters[0] => user name | parameters[1] => follows   | parameters[2] => other user

            // The first parameter is either a user's name or the QUIT command
            String firstParam = parameters[0];

            int paramCount = parameters.length;
            switch (paramCount) {
                case 1:
                    if ("QUIT".equals(firstParam)) {
                        // Exit the application
                        return 1;
                    }
                    // Read a user's timeline
                    read(firstParam);
                    break;

                case 2:
                    // Display a user's wall
                    displayWall(firstParam);
                    break;

                case 3:
                    // POST or FOLLOW
                    String command = parameters[1];
                    String subject = parameters[2];
                    if ("->".equals(command)) {
                        // A user posts a message
                        post(firstParam, subject);
                    } else {
                        // A user follows another user
                        follow(firstParam, subject);
                    }
                    break;

                default:
                    break;
            }

        }
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
            builder.append(wrapper.getFormattedContent());
            builder.append("\n");
        }
        console.writer().println(builder.toString());
    }

}
