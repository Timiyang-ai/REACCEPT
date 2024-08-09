public static boolean isDate(CharSequence input) {
        return isMatch(RegexConstant.REGEX_DATE, input);
    }