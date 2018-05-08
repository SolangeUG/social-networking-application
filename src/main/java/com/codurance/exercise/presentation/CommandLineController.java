package com.codurance.exercise.presentation;

import com.codurance.exercise.application.SocialNetwork;
import com.codurance.exercise.application.SocialNetworkImpl;
import com.codurance.exercise.wrapper.ContentWrapper;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Command-Line Controller
 * @author Solange U. Gasengayire
 */
public class CommandLineController {
    private final SocialNetwork network;

    /**
     * Default constructor
     */
    public CommandLineController() {
        super();
        this.network = new SocialNetworkImpl();
    }

    /**
     * Post a message from the user
     * @param username specified user name
     * @param message the message to be posted
     * @param timestamp when the message is posted
     */
    public void postMessage(String username, String message, LocalDateTime timestamp) {
        throw new RuntimeException("Not yet implemented");
    }

    /**
     * Follow a user
     * @param username specified user name
     * @param subject user to follow
     * @param since when the subscription is started
     */
    public void addFollowing(String username, String subject, LocalDateTime since) {
        throw new RuntimeException("Not yet implemented");
    }

    /**
     * Return a map of a given user content
     * @param user specified user name
     * @return all created content by the user
     */
    public Map<LocalDateTime, ContentWrapper> getUserMessages(String user) {
        throw new RuntimeException("Not yet implemented");
    }

    /**
     * Return a map of a given user content
     * and content from the users they follow
     * @param user specified user
     * @return all content from the user and the users they follow
     */
    public Map<LocalDateTime, ContentWrapper> getAllMessages(String user) {
        throw new RuntimeException("Not yet implemented");
    }


}
