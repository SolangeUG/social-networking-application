package com.codurance.exercise.application;

import java.time.LocalDateTime;

/**
 * API methods definition.
 * @author Solange U. Gasengayire
 */
public interface SocialNetwork {

    /**
     * Create/post content
     * @param username name of the user who posts the content
     * @param content actual content to be posted
     * @param timestamp date and time when the content is created
     */
    void createContent(String username, String content, LocalDateTime timestamp);

    /**
     * Create/add subscription (following)
     * @param username name of the user who posts the content
     * @param subscription subscription information to be created
     * @param timestamp date and time when the content is created
     */
    void createSubscription(String username, String subscription, LocalDateTime timestamp);

    /**
     * Get created content for a given user as a string
     * @param username specified user name
     * @return a string representing the list of content
     */
    String getContent(String username);

    /**
     * Get an aggregated list of content from a given user as a string
     * and the users they are following (they subscribed to)
     * @param username the specified user name
     * @return a string representing the list of aggregated content
     */
    String getAllContent(String username);
}
