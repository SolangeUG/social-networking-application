package com.codurance.exercise;

import com.codurance.exercise.presentation.CommandLineController;
import com.codurance.exercise.util.Constants;

import java.io.Console;

/**
 * Console-based social networking application
 * @author Solange U. Gasengayire
 */
public class Application {

    /**
     * Application entry point
     * @param args application arguments
     */
    public static void main( String[] args ) {
        // initialize application controller
        CommandLineController controller = new CommandLineController();

        // initialize application UI
        Console console = System.console();

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
            String result = controller.executeInstruction(instruction);
            if ("QUIT".equals(result)) {
                console.writer().println(Constants.EXIT_MESSAGE);
                console.writer().flush();
                console.writer().close();
                System.exit(0);
            } else if(! result.isEmpty()) {
                console.writer().println(result);
                console.writer().flush();
            }

            // read next command
            instruction = console.readLine("> ");
        }
    }

}
