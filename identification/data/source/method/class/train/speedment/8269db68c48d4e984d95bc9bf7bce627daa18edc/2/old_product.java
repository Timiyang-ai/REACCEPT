public static <T, A> ToFloat<T>
    composeToFloat(Function<T, A> first, ToFloat<A> second) {
        return new ComposedToFloat<>(first, second);
    }