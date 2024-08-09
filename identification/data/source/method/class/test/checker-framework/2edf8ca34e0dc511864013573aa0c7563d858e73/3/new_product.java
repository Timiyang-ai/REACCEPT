@SuppressWarnings({
        "regex",
        "all:purity.not.deterministic.call",
        "lock"
    }) // RegexUtil; temp value used in pure method is equal up to equals but not up to ==
    @Pure
    @EnsuresQualifierIf(result = true, expression = "#1", qualifier = Regex.class)
    public static boolean isRegex(final char c) {
        return isRegex(Character.toString(c));
    }