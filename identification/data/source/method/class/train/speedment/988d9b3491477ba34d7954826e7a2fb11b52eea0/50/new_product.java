public static <T> ToDouble<T> pow(ToByte<T> expression, int power) {
        return PowUtil.bytePowInt(expression, power);
    }