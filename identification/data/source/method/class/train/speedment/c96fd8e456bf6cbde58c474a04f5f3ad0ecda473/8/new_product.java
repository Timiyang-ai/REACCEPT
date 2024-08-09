public static <T> ToLong<T> minus(ToLong<T> first, ToInt<T> second) {
        return MinusUtil.longMinusInt(first, second);
    }