public static <T> ToDouble<T> pow(ToFloat<T> expression, int power) {
        return PowUtil.floatPowInt(expression, power);
    }