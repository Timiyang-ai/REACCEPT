@Deprecated
    public static String collect(String string, final CharFunction function)
    {
        return StringIterate.collectChar(string, function::valueOf);
    }