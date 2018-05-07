package com.codurance.exercise.application;

import com.codurance.exercise.wrapper.ContentWrapper;
import com.codurance.exercise.wrapper.SubscriptionWrapper;

import java.util.List;

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
    List<ContentWrapper> getContent(String user);

    /**
     * Get an aggregated list of content from a given user
     * and the users they are following (they subscribed to)
     * @param user the specified user name
     * @return the request list of aggregated content
     */
    List<ContentWrapper> getAllContent(String user);
}
