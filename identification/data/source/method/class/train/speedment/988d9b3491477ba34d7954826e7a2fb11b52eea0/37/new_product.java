public static <T> ToDouble<T> pow(ToShort<T> expression, ToDouble<T> power) {
        return PowUtil.shortPowDouble(expression, power);
    }