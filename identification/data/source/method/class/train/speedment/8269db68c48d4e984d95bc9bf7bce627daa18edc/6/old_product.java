public static <T, A> ToDouble<T>
    composeToDouble(Function<T, A> first, ToDouble<A> second) {
        return new ComposedToDouble<>(first, second);
    }