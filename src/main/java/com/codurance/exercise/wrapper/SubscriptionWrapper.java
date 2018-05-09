package com.codurance.exercise.wrapper;

import java.time.LocalDateTime;

/**
 * A wrapper class to hold subscription information.
 * @author Solange U. Gasengayire
 */
public class SubscriptionWrapper {
    private final String subscriptionOwner;
    private final String subscriptionSubject;
    private final LocalDateTime subscriptionTimestamp;

    /**
     * Constructor
     * @param owner subscription owner
     * @param subject subscription subject
     * @param since subscription start date
     */
    public SubscriptionWrapper(String owner, String subject, LocalDateTime since) {
        this.subscriptionOwner = owner;
        this.subscriptionSubject = subject;
        this.subscriptionTimestamp = since;
    }

    /**
     * Return the owner of this subscription
     * @return subscription owner
     */
    public String getSubscriptionOwner() {
        return subscriptionOwner;
    }

    /**
     * Return the subject of this subscription
     * @return subscription subject
     */
    public String getSubject() {
        return subscriptionSubject;
    }

    /**
     * Return the start date of this subscription
     * @return subscription start date
     */
    public LocalDateTime getSubscriptionTimestamp() {
        return subscriptionTimestamp;
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + subscriptionOwner.hashCode();
        result = prime * result + subscriptionSubject.hashCode();
        result = prime * result + subscriptionTimestamp.hashCode();
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

        SubscriptionWrapper other = (SubscriptionWrapper) obj;
        return subscriptionOwner.equals(other.getSubscriptionOwner())
                && subscriptionSubject.equals(other.getSubject())
                && subscriptionTimestamp.equals(other.getSubscriptionTimestamp());
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
        return String.format(
                "%s follows %s since %s",
                subscriptionOwner,
                subscriptionSubject,
                subscriptionTimestamp
        );
    }
}
