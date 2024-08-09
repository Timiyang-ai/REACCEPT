public static String matches(String regex) {
        return reportMatcher(new Matches(regex)).returnString();
    }