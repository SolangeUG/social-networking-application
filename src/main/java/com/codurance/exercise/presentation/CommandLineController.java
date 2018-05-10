package com.codurance.exercise.presentation;

import com.codurance.exercise.application.SocialNetwork;
import com.codurance.exercise.application.SocialNetworkImpl;
import com.codurance.exercise.wrapper.ContentWrapper;
import com.codurance.exercise.wrapper.SubscriptionWrapper;

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
     * Execute input command from the user
     * @param instruction user command (as astring)
     * @return result of the command
     */
    public String executeInstruction(String instruction) {
        Command command = new Command(instruction);
        String operator = command.getOperator();
        String operand = command.getOperand();

        String result = "";

        switch (command.getOperation()) {
            case POST:
                postMessage(operator, operand, LocalDateTime.now());
                break;

            case READ:
                result = getContentAsString(getUserMessages(operator), false);
                break;

            case FOLLOW:
                addFollowing(operator, operand, LocalDateTime.now());
                break;

            case WALL:
                result = getContentAsString(getAllMessages(operator), true);
                break;

            case QUIT:
                result = "QUIT";
                break;

            default:
                break;
        }

        return result;
    }

    /**
     * Post a message from the user
     * @param username specified user name
     * @param message the message to be posted
     * @param timestamp when the message is posted
     */
    void postMessage(String username, String message, LocalDateTime timestamp) {
        ContentWrapper content = new ContentWrapper(username, message, timestamp);
        this.network.createContent(content);
    }

    /**
     * Follow a user
     * @param username specified user name
     * @param subject user to follow
     * @param since when the subscription is started
     */
    void addFollowing(String username, String subject, LocalDateTime since) {
        SubscriptionWrapper subscription = new SubscriptionWrapper(username, subject, since);
        this.network.createSubscription(subscription);
    }

    /**
     * Return a map of a given user content
     * @param user specified user name
     * @return all created content by the user
     */
    Map<LocalDateTime, ContentWrapper> getUserMessages(String user) {
        return this.network.getContent(user);
    }

    /**
     * Return a map of a given user content
     * and content from the users they follow
     * @param user specified user
     * @return all content from the user and the users they follow
     */
    Map<LocalDateTime, ContentWrapper> getAllMessages(String user) {
        return this.network.getAllContent(user);
    }

    /**
     * Return requested content as a string
     * @param content requested content
     * @param withUser flag to include (or not) the user's name
     * @return a string representing the content
     */
    private String getContentAsString(Map<LocalDateTime, ContentWrapper> content, boolean withUser) {
        StringBuilder builder = new StringBuilder();
        for (ContentWrapper wrapper: content.values()) {
            if (withUser) {
                builder.append(wrapper.toString());
            } else {
                builder.append(wrapper.getFormattedContent());
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
