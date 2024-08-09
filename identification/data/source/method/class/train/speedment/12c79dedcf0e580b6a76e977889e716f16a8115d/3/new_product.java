@SuppressWarnings("varargs")
    @SafeVarargs
    public static <T> ToString<T> joining(
            ToString<T>... expressions) {
        return joining("", expressions);
    }