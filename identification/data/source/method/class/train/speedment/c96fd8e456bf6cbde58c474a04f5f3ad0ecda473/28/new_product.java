public static <T> ToDouble<T> minus(ToDouble<T> first, ToLong<T> second) {
        return MinusUtil.doubleMinusLong(first, second);
    }