package com.heartbeat.common.cli;

import static java.lang.String.format;

/**
 * A validation class for command line argument validations.
 * Best to use at Main class of your application with static import.
 */
public final class ArgumentValidations {
    private ArgumentValidations() {

    }


    /**
     * Checks the number of arguments according to the minSize. If there are missing args, throws an exception with the index information.
     * Also checks each element of the args array for being null or empty value.
     *
     * @param args    arguments to validate
     * @param minSize minimum size of the arguments.
     */
    public static final void checkLengthAndValue(String[] args, int minSize) throws ArgumentException {
        checkLength(args, minSize);
        checkValues(args);
    }

    /**
     * Checks the number of arguments according to the minSize.
     * If there are missing args, throws an exception with the size information.
     *
     * @param args    arguments to validate
     * @param minSize minimum size of the arguments.
     */
    public static final void checkLength(String[] args, int minSize) throws ArgumentException {
        if (args.length < minSize) {
            throw new ArgumentException(format("Missing arguments Expected: %d Current:%d", minSize, args.length));
        }
    }

    /**
     * Checks each element of the args array for being null or empty value.
     * If there are missing args, throws an exception with the index and value information.
     *
     * @param args arguments to validate
     */
    public static final void checkValues(String[] args) throws ArgumentException {
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null || args[i].length() == 0) {
                throw new ArgumentException(format("Missing argument at Index: %d Value: %s", i, (args[i] == null ? "NULL" : "EMPTY")));
            }
        }

    }
}