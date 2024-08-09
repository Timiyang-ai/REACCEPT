public static <T> ToLong<T> plus(ToShort<T> first, long second) {
        return PlusUtil.shortPlusLong(first, second);
    }