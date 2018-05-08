package com.codurance.exercise;

import com.codurance.exercise.presentation.CommandLineController;

import java.io.Console;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Console-based social networking application
 * @author Solange U. Gasengayire
 */
public class Application {
    private final CommandLineController controller
            = new CommandLineController();

    private static ResourceBundle bundle = ResourceBundle.getBundle(
            "messages",
            Locale.getDefault());

    public static void main( String[] args ) {
        Console console = System.console();
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
            int paramCount = parameters.length;
            // assume user input command is well-formed
            for (int i = 0; i < paramCount; i++) {
                // TODO: implement different command cases
            }

        }
        return 0;
    }

    /**
     * Message post feature
     * @param user name of the user initiating the action
     * @param message message to be posted
     */
    private void post(String user, String message) {
        throw new RuntimeException("Not yet implemented");
    }

    /**
     * Following feature
     * @param user name of the user initiating the action
     * @param subscription name of the user they're following
     */
    private void follow(String user, String subscription) {
        throw new RuntimeException("Not yet implemented");
    }

    /**
     * Display a given user's timeline
     * @param user name of the user initiating the action
     */
    private void read(String user) {
        throw new RuntimeException("Not yet implemented");
    }

    /**
     * Display a given user's timeline, including
     * timelines of all their subscriptions
     * @param user name of the user initiating the action
     */
    private void displayWall(String user) {
        throw new RuntimeException("Not yet implemented");
    }

}
