public static boolean isDate(CharSequence input) {
        return isMatch(RegexConstants.REGEX_DATE, input);
    }