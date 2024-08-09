@Pure
    @EnsuresQualifierIf(result = true, expression = "#1", qualifier = Regex.class)
    public static boolean isRegex(String s) {
        return isRegex(s, 0);
    }