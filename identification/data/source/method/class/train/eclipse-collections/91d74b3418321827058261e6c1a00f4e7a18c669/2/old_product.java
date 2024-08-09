public static int occurrencesOfCodePoint(String string, final int value)
    {
        return StringIterate.countCodePoint(string, new CodePointPredicate()
        {
            public boolean accept(int codePoint)
            {
                return value == codePoint;
            }
        });
    }