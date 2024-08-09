public static int occurrencesOfCodePoint(String string, final int value)
    {
        return StringIterate.countCodePoint(string, codePoint -> value == codePoint);
    }