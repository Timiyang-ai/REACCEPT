public static <T> ToDouble<T> pow(ToLong<T> expression, ToDouble<T> power) {
        return PowUtil.longPowDouble(expression, power);
    }