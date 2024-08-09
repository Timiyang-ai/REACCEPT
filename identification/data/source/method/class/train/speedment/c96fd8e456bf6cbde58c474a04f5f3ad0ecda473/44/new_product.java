public static <T> ToLong<T> plus(ToLong<T> first, ToInt<T> second) {
        return PlusUtil.longPlusInt(first, second);
    }