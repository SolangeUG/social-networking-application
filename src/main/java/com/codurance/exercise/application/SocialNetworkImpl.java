package com.codurance.exercise.application;

import com.codurance.exercise.domain.Connection;
import com.codurance.exercise.domain.Content;
import com.codurance.exercise.domain.Message;
import com.codurance.exercise.domain.User;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Social network API methods implementation.
 * @author Solange U. Gasengayire
 */
public class SocialNetworkImpl implements SocialNetwork {
    private Map<String, User> users = new HashMap<>();

    /**
     * Create/post content
     * @param username  name of the user who posts the content
     * @param content   actual content to be posted
     * @param timestamp date and time when the content is created
     */
    @Override
    public void createContent(String username, String content, LocalDateTime timestamp) {
        if (content != null && !content.isEmpty()) {
            User user = users.get(username);

            // new (unknown) user
            if (user == null) {
                user = new User(username);
                users.put(username, user);
            }

            // save user's message
            Message message = new Message(user, content, timestamp);
            user.addContent(message);
        }
    }

    /**
     * Create/add subscription (following)
     * @param username     name of the user who posts the content
     * @param subscription subscription information to be created
     * @param timestamp    date and time when the content is created
     */
    @Override
    public void createSubscription(String username, String subscription, LocalDateTime timestamp) {
        if (subscription != null && !subscription.isEmpty()) {

            // assume that only "known" users can follow other users
            User user = users.get(username);
            if (user != null) {
                // assume that a user can only follow a "known" user
                User target = users.get(subscription);
                if (target != null) {
                    Connection link = new Connection(user, target, timestamp);
                    user.addConnection(link);
                }
            }
        }
    }

    /**
     * Get created content for a given user as a string
     * @param username specified user name
     * @return a string representing the list of content
     */
    @Override
    public String getContent(String username) {
        User user = users.get(username);
        return getMapAsString(user.getAllContent(), false);
    }

    /**
     * Get an aggregated list of content from a given user as a string
     * and the users they are following (they subscribed to)
     * @param username the specified user name
     * @return a string representing the list of aggregated content
     */
    @Override
    public String getAllContent(String username) {
        TreeMap<LocalDateTime, Content> allContent = getUserAggregatedContent(username);
        return getMapAsString(allContent,true);
    }

    /**
     * Get created content for a given user
     * @param username specified user name
     * @return the request list of content
     */
    Map<LocalDateTime, Content> getUserContent(String username) {
        User user = users.get(username);
        return user.getAllContent();
    }

    /**
     * Get an aggregated map of content from a given user
     * and the users they are following (they subscribed to)
     * @param username the specified user name
     * @return the request map of aggregated content
     */
    TreeMap<LocalDateTime, Content> getUserAggregatedContent(String username) {
        TreeMap<LocalDateTime, Content> allContent = new TreeMap<>(Comparator.reverseOrder());

        User requestedUser = users.get(username);
        if (requestedUser != null) {
            // add the requested user to the list of their subscriptions
            List<User> following = requestedUser.getSubscriptions();
            following.add(requestedUser);

            // build an aggregated list of the requested user's messages
            // and all the messages of the users they follow
            for (User user: following) {
                allContent.putAll(user.getAllContent());
            }
        }
        return allContent;
    }

    /**
     * Return a string representing the content of a map
     * @param map the specified map of content
     * @param withUser include user name
     * @return the map as a string
     */
    private String getMapAsString(Map<LocalDateTime, Content> map, boolean withUser) {
        StringBuilder builder = new StringBuilder();
        for (Content content: map.values()) {
            String contentStr = withUser ? content.toString() : content.formatContent();
            builder.append(contentStr);
            builder.append("\n");
        }
        return builder.toString();
    }
}
