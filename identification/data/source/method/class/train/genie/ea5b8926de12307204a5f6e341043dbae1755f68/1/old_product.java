public static String[] splitCmdLine(String input)
            throws CloudServiceException {
        LOG.debug("Command line: " + input);
        if (input == null) {
            return new String[0];
        }

        String[] output = null;
        try {
            // ignore delimiter if it is within quotes
            output = input.trim().split("[" + ARG_DELIMITER
                    + "]+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        } catch (Exception e) {
            String msg = "Invalid argument: " + input;
            LOG.error(msg, e);
            throw new CloudServiceException(
                    HttpURLConnection.HTTP_INTERNAL_ERROR, msg, e);
        }

        // "cleanse" inputs - get rid of enclosing quotes
        for (int i = 0; i < output.length; i++) {
            // double quotes
            if ((output[i].startsWith("\"") && (output[i].endsWith("\"")))) {
                output[i] = output[i].replaceAll("(^\")|(\"$)", "");
            }
            LOG.debug(i + ": " + output[i]);
        }
        return output;
    }