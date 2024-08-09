public static boolean isUsername(CharSequence input) {
        return isMatch(RegexConstant.REGEX_USERNAME, input);
    }