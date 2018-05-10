package com.codurance.exercise.wrapper;

import com.codurance.exercise.util.Constants;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * A wrapper class to hold content information.
 * @author Solange U. Gasengayire
 */
public class ContentWrapper {
    private final String owner;
    private final String content;
    private final LocalDateTime creationTimestamp;
    private ResourceBundle messageBundle;

    /**
     * Constructor
     * @param owner content owner/author
     * @param message actual content
     * @param timestamp content creation timestamp
     */
    public ContentWrapper(String owner, String message, LocalDateTime timestamp) {
        this.owner = owner;
        this.content = message;
        this.creationTimestamp = timestamp;

        this.messageBundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    }

    /**
     * Constructor
     * @param owner content owner/author
     * @param message actual content
     * @param timestamp content creation timestamp
     * @param locale context locale
     */
    ContentWrapper(String owner, String message, LocalDateTime timestamp, Locale locale) {
        this.owner = owner;
        this.content = message;
        this.creationTimestamp = timestamp;

        this.messageBundle = ResourceBundle.getBundle("messages", locale);
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
    public LocalDateTime getCreationTimestamp() {
        return this.creationTimestamp;
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
        return String.format(
                "%s - %s",
                owner,
                getFormattedContent()
        );
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + owner.hashCode();
        result = prime * result + content.hashCode();
        result = prime * result + creationTimestamp.hashCode();
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

        ContentWrapper other = (ContentWrapper) obj;
        return owner.equals(other.getOwner())
                && content.equals(other.getContent())
                && creationTimestamp.equals(other.getCreationTimestamp());
    }

    /**
     * Return a string representation of this wrapper's content
     * @return wrapper content with duration
     */
    public String getFormattedContent() {
        return content + " " + formatDuration();
    }

    /**
     * Return a string representation of the time difference
     * between this content creation date and the time at which this
     * method is called.
     * @return time difference as string
     */
    private String formatDuration() {
        Duration timeDiff = Duration.between(creationTimestamp, LocalDateTime.now());
        String result;

        long duration = timeDiff.toDays();
        if (duration >= 1) {
            result = formatDurationWithUnit(Constants.CHRONO_UNIT_DAY, duration);
        } else {
            duration = timeDiff.toHours();
            if (duration >= 1) {
                result = formatDurationWithUnit(Constants.CHRONO_UNIT_HOUR, duration);
            } else {
                duration = timeDiff.toMinutes();
                if (duration >= 1) {
                    result = formatDurationWithUnit(Constants.CHRONO_UNIT_MINUTE, duration);
                } else {
                    duration = timeDiff.toMillis() / 1000;
                    result = formatDurationWithUnit(Constants.CHRONO_UNIT_SECOND, duration);
                }
            }
        }
        return result;
    }

    /**
     * Return a localized string representation of a duration
     * @param unit duration unit
     * @param duration duration amount
     * @return localized string representation of duration
     */
    private String formatDurationWithUnit(String unit, long duration) {
        String messageKey = Constants.TIME_DIFFERENCE_PREFIX + unit;
        String result = messageBundle.getString(messageKey);
        if (duration > 1) {
            messageKey += Constants.TIME_DIFFERENCE_SUFFIX;
            result = String.format(
                    messageBundle.getString(messageKey),
                    duration
            );
        }
        return result;
    }
}
