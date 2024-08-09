public static <T> ToDouble<T> pow(ToShort<T> expression, ToInt<T> power) {
        return PowUtil.shortPowInt(expression, power);
    }