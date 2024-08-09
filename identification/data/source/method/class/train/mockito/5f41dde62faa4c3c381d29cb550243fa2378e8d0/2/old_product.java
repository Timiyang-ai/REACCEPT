public static String matches(String regex) {
        LastArguments.reportMatcher(new Matches(regex));
        return null;
    }