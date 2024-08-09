public static <T> ToDouble<T>
    pow(ToShort<T> expression, ToDouble<T> power) {
        return object -> Math.pow(
            expression.applyAsShort(object),
            power.applyAsDouble(object)
        );
    }