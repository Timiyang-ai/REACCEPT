public static boolean isURL(CharSequence input) {
        return isMatch(RegexConstant.REGEX_URL, input);
    }