public static <T, A> ToBigDecimal<T>
    composeToBigDecimal(Function<T, A> first, ToBigDecimal<A> second) {
        return new ComposedToBigDecimal<>(first, second);
    }