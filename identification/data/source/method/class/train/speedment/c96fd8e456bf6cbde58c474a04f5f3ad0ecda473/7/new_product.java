public static <T> ToInt<T> plus(ToShort<T> first, int second) {
        return PlusUtil.shortPlusInt(first, second);
    }