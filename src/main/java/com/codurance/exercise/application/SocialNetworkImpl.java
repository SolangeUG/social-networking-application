package com.codurance.exercise.application;

import com.codurance.exercise.wrapper.ContentWrapper;
import com.codurance.exercise.wrapper.SubscriptionWrapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Social network API methods implementation.
 * @author Solange U. Gasengayire
 */
public class SocialNetworkImpl implements SocialNetwork {

    /**
     * Create/post content
     *
     * @param content content information to be created
     */
    @Override
    public void createContent(ContentWrapper content) {
        throw new RuntimeException("Not yet implemented");
    }

    /**
     * Create/add subscription (following)
     *
     * @param subscription subscription information to be created
     */
    @Override
    public void createSubscription(SubscriptionWrapper subscription) {
        throw new RuntimeException("Not yet implemented");
    }

    /**
     * Get created content for a given user
     *
     * @param user specified user name
     * @return the request list of content
     */
    @Override
    public Map<LocalDateTime, ContentWrapper> getContent(String user) {
        throw new RuntimeException("Not yet implemented");
    }

    /**
     * Get an aggregated map of content from a given user
     * and the users they are following (they subscribed to)
     *
     * @param user the specified user name
     * @return the request map of aggregated content
     */
    @Override
    public Map<LocalDateTime, ContentWrapper> getAllContent(String user) {
        throw new RuntimeException("Not yet implemented");
    }
}
