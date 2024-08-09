public static String contains(String substring) {
        return reportMatcher(new Contains(substring)).<String>nullValue();
    }