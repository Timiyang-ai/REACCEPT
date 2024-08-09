public static AssertionError createArgumentsAreDifferentException(String message, String wanted, String actual) {
        if (hasJUnit) {
            return createJUnitArgumentsAreDifferent(message, wanted, actual);
        }
        return new ArgumentsAreDifferent(message);
    }