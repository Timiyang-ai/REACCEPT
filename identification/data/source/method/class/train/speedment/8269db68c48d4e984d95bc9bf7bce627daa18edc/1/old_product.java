public static <T, A> ToChar<T>
    composeToChar(Function<T, A> first, ToChar<A> second) {
        return new ComposedToChar<>(first, second);
    }