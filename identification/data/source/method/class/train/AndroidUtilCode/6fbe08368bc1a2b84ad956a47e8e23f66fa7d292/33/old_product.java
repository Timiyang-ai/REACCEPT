public static boolean isEmail(CharSequence input) {
        return isMatch(RegexConstant.REGEX_EMAIL, input);
    }