package com.codurance.exercise.application;

import com.codurance.exercise.domain.Connection;
import com.codurance.exercise.domain.Content;
import com.codurance.exercise.domain.Message;
import com.codurance.exercise.domain.User;
import com.codurance.exercise.wrapper.ContentWrapper;
import com.codurance.exercise.wrapper.SubscriptionWrapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Social network API methods implementation.
 * @author Solange U. Gasengayire
 */
public class SocialNetworkImpl implements SocialNetwork {
    private Map<String, User> users = new HashMap<>();

    /**
     * Create/post content
     * @param content content information to be created
     */
    @Override
    public void createContent(ContentWrapper content) {
        if (content != null) {
            String username = content.getOwner();
            User user = users.get(username);

            // new (unknown) user
            if (user == null) {
                user = new User(username);
                users.put(username, user);
            }

            // save user's message
            Message message = new Message(user, content.getContent(), content.getCreationTimestamp());
            user.addContent(message);
        }
    }

    /**
     * Create/add subscription (following)
     * @param subscription subscription information to be created
     */
    @Override
    public void createSubscription(SubscriptionWrapper subscription) {
        if (subscription != null) {
            String username = subscription.getSubscriptionOwner();

            // assume that only "known" users can follow other users
            User user = users.get(username);
            if (user != null) {
                String other = subscription.getSubject();

                // assume that a user can only follow a "known" user
                User target = users.get(other);
                if (target != null) {
                    Connection link = new Connection(user, target, subscription.getSubscriptionTimestamp());
                    user.addConnection(link);
                }
            }
        }
    }

    /**
     * Get created content for a given user
     * @param username specified user name
     * @return the request list of content
     */
    @Override
    public Map<LocalDateTime, ContentWrapper> getContent(String username) {
        Map<LocalDateTime, ContentWrapper> userContent = new TreeMap<>();

        User user = users.get(username);
        if (user != null) {
            for (Content content: user.getAllContent()) {
                Message message = (Message) content;
                LocalDateTime timestamp = message.getCreationDate();
                ContentWrapper wrapper = new ContentWrapper(
                        user.getName(), message.getContent(), timestamp);
                userContent.put(timestamp, wrapper);
            }
        }

        return userContent;
    }

    /**
     * Get an aggregated map of content from a given user
     * and the users they are following (they subscribed to)
     * @param username the specified user name
     * @return the request map of aggregated content
     */
    @Override
    public Map<LocalDateTime, ContentWrapper> getAllContent(String username) {
        TreeMap<LocalDateTime, ContentWrapper> allContent = new TreeMap<>();

        User requestedUser = users.get(username);
        if (requestedUser != null) {
            // add the requested user to the list of their subscriptions
            List<User> following = requestedUser.getSubscriptions();
            following.add(requestedUser);

            // return an aggregated list of the requested user's messages
            // and all the messages of the users they follow
            for (User user: following) {
                allContent.putAll(getContent(user.getName()));
            }
        }

        return allContent;
    }
}
