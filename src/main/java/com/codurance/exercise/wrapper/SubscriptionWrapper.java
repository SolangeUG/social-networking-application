package com.codurance.exercise.wrapper;

import java.time.LocalDate;

/**
 * A wrapper class to hold subscription information.
 * @author Solange U. Gasengayire
 */
public class SubscriptionWrapper {
    private final String subscriptionOwner;
    private final String subscriptionSubject;
    private final LocalDate subscriptionDate;

    /**
     * Constructor
     * @param owner subscription owner
     * @param subject subscription subject
     * @param since subscription start date
     */
    public SubscriptionWrapper(String owner, String subject, LocalDate since) {
        this.subscriptionOwner = owner;
        this.subscriptionSubject = subject;
        this.subscriptionDate = since;
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
    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }
}
