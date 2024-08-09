public static <T, A> ToString<T>
    composeToString(Function<T, A> first, ToString<A> second) {
        return new ComposedToString<>(first, second);
    }