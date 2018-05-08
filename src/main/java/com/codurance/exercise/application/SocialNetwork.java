package com.codurance.exercise.application;

import com.codurance.exercise.wrapper.ContentWrapper;
import com.codurance.exercise.wrapper.SubscriptionWrapper;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * API methods definition.
 * @author Solange U. Gasengayire
 */
public interface SocialNetwork {

    /**
     * Create/post content
     * @param content content information to be created
     */
    void createContent(ContentWrapper content);

    /**
     * Create/add subscription (following)
     * @param subscription subscription information to be created
     */
    void createSubscription(SubscriptionWrapper subscription);

    /**
     * Get created content for a given user
     * @param user specified user name
     * @return the request list of content
     */
    Map<LocalDateTime, ContentWrapper> getContent(String user);

    /**
     * Get an aggregated map of content from a given user
     * and the users they are following (they subscribed to)
     * @param user the specified user name
     * @return the request map of aggregated content
     */
    Map<LocalDateTime, ContentWrapper> getAllContent(String user);
}
