public static <T> ToDouble<T> plus(ToDouble<T> first, ToInt<T> second) {
        return PlusUtil.doublePlusInt(first, second);
    }