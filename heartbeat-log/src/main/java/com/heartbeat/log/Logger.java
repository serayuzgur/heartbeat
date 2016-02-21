package com.heartbeat.log;

import java.util.Calendar;

import static java.lang.String.format;

/**
 * A static com.heartbeat.log.Logger class. Aims to be simple , light-weight and fast.
 * It has only one template for now
 * DEBUG_LEVEL  [YYYY-mm-DD hh:MM:ss,SSS] TAG: Message
 */
public final class Logger {
    private static Level defaultPackageLevel = Level.INFO;

    //TODO: Appender  system
    //TODO: Set printwriters of Exceptions
    //TODO: Tag-level checks.


    private Logger() {
    }

    public static Level getLevel() {
        return defaultPackageLevel;
    }

    public static void setLevel(Level defaultPackageLevel) {
        Logger.defaultPackageLevel = defaultPackageLevel;
    }

    public static final void trace(String tag, String message) {
        print(Level.TRACE, tag, message);

    }

    public static final void trace(String tag, String message, Object... args) {
        print(Level.TRACE, tag, message, args);

    }

    public static final void debug(String tag, String message) {
        print(Level.DEBUG, tag, message);

    }

    public static final void debug(String tag, String message, Object... args) {
        print(Level.DEBUG, tag, message, args);

    }

    public static final void info(String tag, String message) {
        print(Level.INFO, tag, message);

    }

    public static final void info(String tag, String message, Object... args) {
        print(Level.INFO, tag, message, args);
    }

    public static final void warn(String tag, String message) {
        print(Level.WARNING, tag, message);
    }

    public static final void warn(String tag, String message, Object... args) {
        print(Level.WARNING, tag, message, args);
    }

    public static final void error(String tag, String message) {
        print(Level.ERROR, tag, message);
    }

    public static final void error(String tag, String message, Object... args) {
        print(Level.ERROR, tag, message, args);
    }

    public static final void error(String tag, String message, Exception ex) {
        print(Level.ERROR, tag, message);
        ex.printStackTrace(System.out);
    }

    public static final void error(String tag, String message, Exception ex, Object... args) {
        print(Level.ERROR, tag, message, args);
        ex.printStackTrace(System.out);
    }


    private static final void print(Level level, String tag, String message) {
        if (defaultPackageLevel.ordinal() < level.ordinal())
            return;
        StringBuilder builder = getBuilderWithDate(level, tag);
        builder.append(message);
        System.out.println(builder.toString());
    }


    private static final void print(Level level, String tag, String message, Object... args) {
        if (defaultPackageLevel.ordinal() < level.ordinal())
            return;
        StringBuilder builder = getBuilderWithDate(level, tag);
        builder.append(format(message, args));
        System.out.println(builder.toString());
    }

    private static final StringBuilder getBuilderWithDate(Level level, String tag) {
        StringBuilder builder = new StringBuilder();
        Calendar now = Calendar.getInstance();
        builder.append(level.name());
        builder.append(" \t");
        builder.append('[');
        builder.append(now.get(Calendar.YEAR));
        builder.append('-');
        builder.append(now.get(Calendar.MONTH));
        builder.append('-');
        builder.append(now.get(Calendar.DAY_OF_MONTH));
        builder.append(' ');
        builder.append(now.get(Calendar.HOUR_OF_DAY));
        builder.append(':');
        builder.append(now.get(Calendar.MINUTE));
        builder.append(':');
        builder.append(now.get(Calendar.SECOND));
        builder.append(',');
        builder.append(now.get(Calendar.MILLISECOND));
        builder.append(']');
        builder.append('\t');
        builder.append(tag);
        builder.append(": \t");
        return builder;
    }

    public enum Level {
        TRACE,
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }
}
