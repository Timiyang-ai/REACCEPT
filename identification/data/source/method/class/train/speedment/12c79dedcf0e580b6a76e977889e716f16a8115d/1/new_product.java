@SuppressWarnings("varargs")
    @SafeVarargs
    public static <T> ToString<T> joining(
            CharSequence separator,
            ToString<T>... expressions) {
        return joining(separator, "", "", expressions);
    }