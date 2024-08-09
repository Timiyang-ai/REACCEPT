public static boolean isIP(CharSequence input) {
        return isMatch(RegexConstant.REGEX_IP, input);
    }