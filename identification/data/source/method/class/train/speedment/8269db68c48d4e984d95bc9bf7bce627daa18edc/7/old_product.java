public static <T, A> ToByte<T>
    composeToByte(Function<T, A> first, ToByte<A> second) {
        return new ComposedToByte<>(first, second);
    }