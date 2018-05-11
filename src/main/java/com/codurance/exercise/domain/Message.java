package com.codurance.exercise.domain;

import java.time.LocalDateTime;

/**
 * A class to represent messages that are
 * posted and shared by users in a social network
 * @author Solange U. Gasengayire
 */
public class Message extends Content {
    private String content;

    /**
     * Constructor
     * @param author message author
     * @param message actual content of the message
     * @param timestamp date on which the message is posted
     */
    public Message(User author, String message, LocalDateTime timestamp) {
        owner = author;
        content = message;
        creationDate = timestamp;
    }

    /**
     * Return actual content
     * @return content
     */
    @Override
    public String getContent() {
        return content;
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime + result + owner.hashCode();
        result = prime + result + content.hashCode();
        result = prime + result + creationDate.hashCode();
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

        Message other = (Message) obj;
        return owner.equals(other.owner)
                && content.equals(other.content)
                && creationDate.equals(other.creationDate);
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
