package com.codurance.exercise.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility constants class
 * @author Solange U. Gasengayire
 */
public abstract class Constants {

    private static ResourceBundle bundle =
            ResourceBundle.getBundle( "messages", Locale.getDefault());

    public static final String STARTUP_MESSAGE =
            bundle.getString("application.messages.startup");

    public static final String PROMPT_MESSAGE =
            bundle.getString("application.messages.prompt");

    public static final String CONSOLE_ERROR_MESSAGE =
            bundle.getString("application.messages.error.console");

    public static final String EXIT_MESSAGE =
            bundle.getString("application.messages.exit");

    public static final String CHRONO_UNIT_DAY = "day";
    public static final String CHRONO_UNIT_HOUR = "hour";
    public static final String CHRONO_UNIT_MINUTE = "minute";
    public static final String CHRONO_UNIT_SECOND = "second";
    public static final String TIME_DIFFERENCE_PREFIX = "content.time.difference.";
    public static final String TIME_DIFFERENCE_SUFFIX = "s";

}
