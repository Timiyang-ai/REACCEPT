public static <T> ToLong<T> plus(ToInt<T> first, long second) {
        return PlusUtil.intPlusLong(first, second);
    }