@EnsuresQualifierIf(result=true, expression="#1",
            qualifier=org.checkerframework.checker.experimental.regex_qual.qual.Regex.class)
    public static boolean isRegex(final char c) {
        return org.checkerframework.checker.regex.RegexUtil.isRegex(c);
    }