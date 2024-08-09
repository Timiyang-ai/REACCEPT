public static AssertionError createArgumentsAreDifferentException(String message, String wanted, String actual) {
        return factory.create(message, wanted, actual);
    }