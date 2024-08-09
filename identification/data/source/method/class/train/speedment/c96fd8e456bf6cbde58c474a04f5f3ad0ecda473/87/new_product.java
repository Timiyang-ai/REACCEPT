public static <T> ToShort<T> plus(ToByte<T> first, byte second) {
        return PlusUtil.bytePlusByte(first, second);
    }