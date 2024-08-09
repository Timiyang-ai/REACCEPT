public static <T> ToDouble<T> pow(ToLong<T> expression, ToDouble<T> power) {
        return object -> Math.pow(
            expression.applyAsLong(object),
            power.applyAsDouble(object)
        );
    }