public static <T> ToDouble<T> pow(ToFloat<T> expression, double power) {
        return PowUtil.floatPowDouble(expression, power);
    }