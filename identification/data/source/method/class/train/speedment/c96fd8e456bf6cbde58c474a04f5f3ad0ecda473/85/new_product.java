public static <T> ToDouble<T> minus(ToDouble<T> first, ToInt<T> second) {
        return MinusUtil.doubleMinusInt(first, second);
    }