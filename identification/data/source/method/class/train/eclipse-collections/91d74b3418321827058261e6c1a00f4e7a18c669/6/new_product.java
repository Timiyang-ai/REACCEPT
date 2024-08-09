@Deprecated
    public static String collect(String string, CharFunction function)
    {
        return StringIterate.collectChar(string, function::valueOf);
    }