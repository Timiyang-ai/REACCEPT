public static <T, A> ToShortNullable<T> composeToShort(Function<T, A> before, ToShort<A> after) {
        return new ComposeToShort<>(before, after);
    }