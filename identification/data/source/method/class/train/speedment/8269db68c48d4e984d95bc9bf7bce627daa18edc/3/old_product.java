public static <T, A> ToInt<T>
    composeToInt(Function<T, A> first, ToInt<A> second) {
        return new ComposedToInt<>(first, second);
    }