public static <T, A> ToCharNullable<T> composeToChar(Function<T, A> before, ToChar<A> after) {
        return new ComposeToChar<>(before, after);
    }