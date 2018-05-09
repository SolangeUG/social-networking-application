package com.codurance.exercise.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * A class that represents a link between users
 * in a social network
 * @author Solange U. Gasengayire
 */
public class Connection {
    private User origin;
    private User target;
    private LocalDateTime startDate;

    /**
     * Constructor
     * @param from start point of the link (as a user)
     * @param to end point of the link
     * @param since date on which the link is established
     */
    public Connection(User from, User to, LocalDateTime since) {
        this.origin = from;
        this.target = to;
        this.startDate = since;
    }

    /**
     * Return user who initiated this connection
     * @return origin user
     */
    public User getOrigin() {
        return this.origin;
    }

    /**
     * Return user at the end of this connection
     * @return target user
     */
    public User getTarget() {
        return this.target;
    }

    /**
     * Return date of this connection
     * @return start date
     */
    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + origin.hashCode();
        result = prime * result + target.hashCode();
        result = prime * result + startDate.hashCode();
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

        Connection other = (Connection) obj;
        return origin.equals(other.origin)
                && target.equals(other.target)
                && startDate.equals(other.startDate);
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
        DateTimeFormatter formatter
                = DateTimeFormatter
                    .ofLocalizedDateTime(FormatStyle.FULL)
                    .withLocale(Locale.getDefault());
        return String.format(
                "%s follows %s since %s",
                origin.toString(),
                target.toString(),
                startDate.format(formatter));
    }
}
