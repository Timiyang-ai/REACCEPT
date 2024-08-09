public static <T, A> ToBooleanNullable<T> composeToBoolean(Function<T, A> before, ToBoolean<A> after) {
        return new ComposeToBoolean<>(before, after);
    }