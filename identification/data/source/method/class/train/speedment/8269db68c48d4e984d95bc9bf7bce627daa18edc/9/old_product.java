public static <T, A> ToBoolean<T>
    composeToBoolean(Function<T, A> first, ToBoolean<A> second) {
        return new ComposedToBoolean<>(first, second);
    }