public static <T, A> ToLongNullable<T> composeToLong(Function<T, A> before, ToLong<A> after) {
        return new ComposeToLong<>(before, after);
    }