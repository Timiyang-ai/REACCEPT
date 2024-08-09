public static <T> ToDouble<T>
    pow(ToFloat<T> expression, ToDouble<T> power) {
        return object -> Math.pow(
            expression.applyAsFloat(object),
            power.applyAsDouble(object)
        );
    }