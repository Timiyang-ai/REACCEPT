public static <T> ToDouble<T> minus(ToDouble<T> first, ToDouble<T> second) {
        return MinusUtil.doubleMinusDouble(first, second);
    }