public static <T> ToDouble<T> pow(ToShort<T> expression, int power) {
        return PowUtil.shortPowInt(expression, power);
    }