public static <T> ToDouble<T> minus(ToFloat<T> first, ToLong<T> second) {
        return MinusUtil.floatMinusLong(first, second);
    }