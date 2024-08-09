public static <T, A> ToBigDecimalNullable<T> composeToBigDecimal(Function<T, A> before, ToBigDecimal<A> after) {
        return new ComposeToBigDecimal<>(before, after);
    }