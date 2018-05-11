package com.codurance.exercise.domain;

import com.codurance.exercise.util.Constants;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Abstract class that represents all sorts of content
 * that is created and shared in a social network.
 * @author Solange U. Gasengayire
 */
public abstract class Content {
    User owner;
    LocalDateTime creationDate;
    private ResourceBundle messageBundle =
            ResourceBundle.getBundle("messages", Locale.getDefault());;

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
    LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Return actual content
     * @return content
     */
    public abstract Object getContent();

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
                formatContent()
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
        result = prime * result + getContent().hashCode();
        result = prime * result + creationDate.hashCode();
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

        Content other = (Content) obj;
        return owner.equals(other.getOwner())
                && getContent().equals(other.getContent())
                && creationDate.equals(other.getCreationDate());
    }

    /**
     * Return a string representation of this wrapper's content
     * @return wrapper content with duration
     */
    public String formatContent() {
        return getContent() + " " + formatDuration();
    }

    /**
     * Return a string representation of the time difference
     * between this content creation date and the time at which this
     * method is called.
     * @return time difference as string
     */
    private String formatDuration() {
        Duration timeDiff = Duration.between(creationDate, LocalDateTime.now());
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
