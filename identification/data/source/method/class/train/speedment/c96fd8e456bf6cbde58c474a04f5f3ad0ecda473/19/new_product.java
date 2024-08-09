public static <T> ToInt<T> multiply(ToInt<T> first, ToByte<T> second) {
        return MultiplyUtil.intMultiplyByte(first, second);
    }