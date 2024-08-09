public static boolean isEmail(CharSequence input) {
        return isMatch(REGEX_EMAIL, input);
    }