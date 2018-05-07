package com.codurance.exercise.application;

import com.codurance.exercise.wrapper.ContentWrapper;
import com.codurance.exercise.wrapper.SubscriptionWrapper;

import java.util.List;

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
    public List<ContentWrapper> getContent(String user) {
        throw new RuntimeException("Not yet implemented");
    }

    /**
     * Get an aggregated list of content from a given user
     * and the users they are following (they subscribed to)
     *
     * @param user the specified user name
     * @return the request list of aggregated content
     */
    @Override
    public List<ContentWrapper> getAllContent(String user) {
        throw new RuntimeException("Not yet implemented");
    }
}