public static boolean isMobileExact(CharSequence input) {
        return isMatch(RegexConstant.REGEX_MOBILE_EXACT, input);
    }