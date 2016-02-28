package com.heartbeat.common.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A helper class to exec system commands.
 * It aims to sync the commands and clean the code by handling all outputs with errors.
 */
public class RuntimeCommandExec {

    private RuntimeCommandExec() {

    }

    /**
     * Executes the given command. Returns the output of the command. If any error occurs,
     * method will merge previous output and errors and throw as an exception
     *
     * @param command command to execute
     * @return result of the command.
     * @throws RuntimeCommandException
     */
    public static synchronized String exec(String command) throws RuntimeCommandException {
        try {
            //Create the process with streams assigned.
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // Collect all the output.
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = stdInput.readLine()) != null) {
                result.append(line);
            }

            boolean hasError = false;
            // Collect all the errors to the same builder.
            while ((line = stdError.readLine()) != null) {
                result.append(line);
                hasError = true;
            }

            //In case of error, just merge the outputs together and throw an exception.
            if (hasError) {
                throw new RuntimeCommandException(result.toString());
            }

            return result.toString();

        } catch (IOException e) {
            throw new RuntimeCommandException(e);
        }
    }
}