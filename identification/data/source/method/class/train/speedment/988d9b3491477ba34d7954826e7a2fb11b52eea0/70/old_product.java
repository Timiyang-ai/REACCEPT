public static <T> ToDouble<T> pow(ToByte<T> expression, double power) {
        return object -> Math.pow(expression.applyAsByte(object), power);
    }