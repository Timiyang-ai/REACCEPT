public static <T> ToDouble<T> pow(ToShort<T> expression, double power) {
        return PowUtil.shortPowDouble(expression, power);
    }