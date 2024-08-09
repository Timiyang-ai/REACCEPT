public static <T> ToDouble<T> pow(ToLong<T> expression, int power) {
        return PowUtil.longPowInt(expression, power);
    }