public static <T, A> ToIntNullable<T> composeToInt(Function<T, A> before, ToInt<A> after) {
        return new ComposeToInt<>(before, after);
    }