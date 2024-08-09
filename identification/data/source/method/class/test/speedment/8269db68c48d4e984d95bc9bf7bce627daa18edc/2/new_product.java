public static <T, A> ToBoolean<T> composeToBoolean(Function<T, A> before, ToBoolean<A> after) {
        return new ComposeToBoolean<>(before, after);
    }