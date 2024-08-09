public static Priority[] getAllPossiblePriorities() {
        return new Priority[]{Priority.FATAL, Priority.ERROR, Level.WARN,
            Priority.INFO, Priority.DEBUG};
    }