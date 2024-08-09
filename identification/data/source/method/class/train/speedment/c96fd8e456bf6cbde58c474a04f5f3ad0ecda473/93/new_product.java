public static <T> ToDouble<T> plus(ToDouble<T> first, ToDouble<T> second) {
        return PlusUtil.doublePlusDouble(first, second);
    }