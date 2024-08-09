public static int parseLastInt(String s, int i) {
        try {
            return Integer.parseInt(parseLastString(s));
        } catch (NumberFormatException e) {
            LOG.trace(DEFAULT_LOG_MSG, s, e);
            return i;
        }
    }