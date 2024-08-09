public static <T, A> ToShort<T>
    composeToShort(Function<T, A> first, ToShort<A> second) {
        return new ComposedToShort<>(first, second);
    }