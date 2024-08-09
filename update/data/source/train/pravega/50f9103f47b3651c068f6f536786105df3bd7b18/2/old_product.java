public static void throwIfIllegalArgument(boolean validCondition, String argName, String message, Object... args) throws IllegalArgumentException {
        if (!validCondition) {
            throw new IllegalArgumentException(argName + ": " + String.format(message, args));
        }
    }