public static <T> ToDouble<T> pow(ToLong<T> expression, ToInt<T> power) {
        return PowUtil.longPowInt(expression, power);
    }