public static <T, A> ToStringNullable<T> composeToString(Function<T, A> before, ToString<A> after) {
        return new ComposeToString<>(before, after);
    }