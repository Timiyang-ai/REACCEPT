public static <T, A> ToByteNullable<T> composeToByte(Function<T, A> before, ToByte<A> after) {
        return new ComposeToByte<>(before, after);
    }