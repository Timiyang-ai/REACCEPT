public static <T, A> ToBooleanNullable<T> composeToBooleanNullable(Function<T, A> before, ToBooleanNullable<A> after) {
        return new ComposeToBooleanNullable<>(before, after);
    }