public static String getReplaceFirst(final String input,
                                         final String regex,
                                         final String replacement) {
        if (input == null) return "";
        return Pattern.compile(regex).matcher(input).replaceFirst(replacement);
    }