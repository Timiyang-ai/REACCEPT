public static String contains(String substring) {
        LastArguments.instance().reportMatcher(new Contains(substring));
        return null;
    }