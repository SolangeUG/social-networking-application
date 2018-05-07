package com.codurance.exercise.wrapper;

import java.time.LocalDate;

/**
 * A wrapper class to hold content information.
 * @author Solange U. Gasengayire
 */
public class ContentWrapper {
    private final String owner;
    private final String content;
    private final LocalDate creationDate;

    /**
     * Constructor
     * @param owner content owner/author
     * @param message actual content
     * @param timestamp content creation timestamp
     */
    public ContentWrapper(String owner, String message, LocalDate timestamp) {
        this.owner = owner;
        this.content = message;
        this.creationDate = timestamp;
    }

    /**
     * Return content owner
     * @return owner/author
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * Return actual content
     * @return message
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Get the date on which the content was created
     * @return content creation date
     */
    public LocalDate getCreationDate() {
        return this.creationDate;
    }

}
