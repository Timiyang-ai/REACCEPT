public static String[] getSplits(String input, String regex) {
        if (input == null) return null;
        return input.split(regex);
    }