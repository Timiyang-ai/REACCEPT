public static int occurrencesOfCodePoint(String string, int value)
    {
        return StringIterate.countCodePoint(string, codePoint -> value == codePoint);
    }