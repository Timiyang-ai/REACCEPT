public static <T> ToDouble<T> minus(ToFloat<T> first, long second) {
        return MinusUtil.floatMinusLong(first, second);
    }