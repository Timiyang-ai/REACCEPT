public static <T, A> ToLong<T>
    composeToLong(Function<T, A> first, ToLong<A> second) {
        return new ComposedToLong<>(first, second);
    }