package com.codurance.exercise;

import com.codurance.exercise.presentation.CommandLineController;

/**
 * Console-based social networking application
 * @author Solange U. Gasengayire
 */
public class Application {
    private final CommandLineController controller
            = new CommandLineController();

    public static void main( String[] args ) {
        System.out.println( "Console-based Social Networking Application" );

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
