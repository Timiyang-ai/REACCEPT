public static <K> byte getByteValue(final Map<? super K, ?> map, final K key) {
        Byte byteObject = getByte(map, key);
        if (byteObject == null) {
            return 0;
        }
        return byteObject.byteValue();
    }