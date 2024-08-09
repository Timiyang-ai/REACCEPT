public static <T> ToDouble<T> pow(ToFloat<T> expression, ToInt<T> power) {
        return PowUtil.floatPowInt(expression, power);
    }