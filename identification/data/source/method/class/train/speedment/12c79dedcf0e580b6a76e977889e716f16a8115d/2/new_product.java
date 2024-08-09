@SuppressWarnings("varargs")
    @SafeVarargs
    public static <T> ToString<T> joining(
            CharSequence separator,
            CharSequence prefix,
            CharSequence suffix,
            ToString<T>... expressions) {
        return new JoiningExpressionImpl<>(separator, prefix, suffix, asList(expressions));
    }