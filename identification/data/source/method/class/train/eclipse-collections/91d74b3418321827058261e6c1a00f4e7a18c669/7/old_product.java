@Deprecated
    public static String collect(String string, final CharFunction function)
    {
        return StringIterate.collectChar(string, new CharToCharFunction()
        {
            public char valueOf(char charParameter)
            {
                return function.valueOf(charParameter);
            }
        });
    }