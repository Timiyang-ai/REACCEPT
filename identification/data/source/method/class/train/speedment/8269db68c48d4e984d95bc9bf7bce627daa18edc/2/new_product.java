public static <T, A> ToFloatNullable<T> composeToFloat(Function<T, A> before, ToFloat<A> after) {
        return new ComposeToFloat<>(before, after);
    }