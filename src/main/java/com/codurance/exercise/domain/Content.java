package com.codurance.exercise.domain;

import java.time.LocalDateTime;

/**
 * Abstract class that represents all sorts of content
 * that is created and shared in a social network.
 * @author Solange U. Gasengayire
 */
public abstract class Content {
    User owner;
    LocalDateTime creationDate;

    /**
     * Return this content's owner/author
     * @return owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Return date on which the content was created
     * @return creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Return actual content
     * @return content
     */
    public abstract Object getContent();

}
