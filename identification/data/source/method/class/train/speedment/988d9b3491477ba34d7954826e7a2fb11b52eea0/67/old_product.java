public static <T> ToDouble<T> pow(ToInt<T> expression, ToDouble<T> power) {
        return object -> Math.pow(
            expression.applyAsInt(object),
            power.applyAsDouble(object)
        );
    }