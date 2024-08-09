public static String[] getSplits(final String input, final String regex) {
        if (input == null) return new String[0];
        return input.split(regex);
    }