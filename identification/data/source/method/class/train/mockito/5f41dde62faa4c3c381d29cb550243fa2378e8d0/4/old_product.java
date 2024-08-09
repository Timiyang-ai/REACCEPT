public static String matches(String regex) {
        LastArguments.instance().reportMatcher(new Matches(regex));
        return null;
    }