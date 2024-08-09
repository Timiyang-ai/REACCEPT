public static String[] getSplits(final String input, final String regex) {
        if (input == null) return null;
        return input.split(regex);
    }