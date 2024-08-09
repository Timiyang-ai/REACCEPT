public static <T> ToDouble<T> pow(ToLong<T> expression, double power) {
        return object -> Math.pow(expression.applyAsLong(object), power);
    }