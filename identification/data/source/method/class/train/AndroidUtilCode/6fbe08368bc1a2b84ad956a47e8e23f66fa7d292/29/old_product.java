public static boolean isIP(CharSequence input) {
        return isMatch(RegexConstants.REGEX_IP, input);
    }