package com.codurance.exercise.domain;

import java.time.LocalDateTime;
import java.util.*;

/**
 * A class that represents a user in a social network
 * @author Solange U. Gasengayire
 */
public class User {
    private String name;
    private List<Connection> connections = new LinkedList<>();
    private Map<LocalDateTime, Content> contentMap = new TreeMap<>(Comparator.reverseOrder());

    /**
     * Constructor
     * @param name user name
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Add a connection to another user
     * @param link connection
     * @return {@code true} if the operation succeeds
     *         {@code false} otherwise
     */
    public boolean addConnection(Connection link) {
        if (link == null)
            return false;

        return connections.add(link);
    }

    /**
     * Add contentList to this user's list of contentList
     * @param content the newly created contentList
     * @return {@code true} if the operation succeeds
     *         {@code false} otherwise
     */
    public boolean addContent(Content content) {
        if (content == null)
            return false;
        Content result = contentMap.put(content.getCreationDate(), content);
        return result != null;
    }

    /**
     * Return a list of all the users followed by this user
     * @return subscriptions
     */
    public List<User> getSubscriptions() {
        List<User> subscriptions = new LinkedList<>();
        for (Connection connection: connections) {
            subscriptions.add(connection.getTarget());
        }
        return subscriptions;
    }

    /**
     * Return a map of all content from this user
     * @return content map
     */
    public Map<LocalDateTime, Content> getAllContent() {
        Map<LocalDateTime, Content> result = new TreeMap<>(Comparator.reverseOrder());
        result.putAll(contentMap);
        return result;
    }

    /**
     * Return this user's name
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        result = prime * result + contentMap.hashCode();
        result = prime * result + connections.hashCode();
        return result;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        User other = (User) obj;
        return name.equals(other.name)
                && contentMap.equals(other.contentMap)
                && connections.equals(other.connections);
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("%s", name);
    }
}
