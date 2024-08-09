public static <T> ToDouble<T> pow(ToLong<T> expression, double power) {
        return PowUtil.longPowDouble(expression, power);
    }