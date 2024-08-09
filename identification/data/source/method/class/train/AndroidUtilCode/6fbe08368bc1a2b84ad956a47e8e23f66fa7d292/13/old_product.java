public static boolean isUsername(CharSequence input) {
        return isMatch(RegexConstants.REGEX_USERNAME, input);
    }