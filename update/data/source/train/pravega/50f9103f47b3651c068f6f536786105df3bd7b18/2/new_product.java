private static String badArgumentMessage(String argName, String message, Object... args) {
        return argName + ": " + String.format(message, args);
    }