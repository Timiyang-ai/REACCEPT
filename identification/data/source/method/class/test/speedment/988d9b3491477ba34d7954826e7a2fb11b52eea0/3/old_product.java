public static <T> ToDouble<T> pow(ToByte<T> expression, ToDouble<T> power) {
        return object -> Math.pow(
            expression.applyAsByte(object),
            power.applyAsDouble(object)
        );
    }