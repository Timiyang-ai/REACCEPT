public static <T> ToLong<T> minus(ToInt<T> first, long second) {
        return MinusUtil.intMinusLong(first, second);
    }