public static <T, A> ToDoubleNullable<T> composeToDouble(Function<T, A> before, ToDouble<A> after) {
        return new ComposeToDouble<>(before, after);
    }