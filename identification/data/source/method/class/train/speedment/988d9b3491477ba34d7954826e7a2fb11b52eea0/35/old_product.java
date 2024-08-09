public static <T> ToDouble<T>
    pow(ToDouble<T> expression, ToDouble<T> power) {
        return object -> Math.pow(
            expression.applyAsDouble(object),
            power.applyAsDouble(object)
        );
    }