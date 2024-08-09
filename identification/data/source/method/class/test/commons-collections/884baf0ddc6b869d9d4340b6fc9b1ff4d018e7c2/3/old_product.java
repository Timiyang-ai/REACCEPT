public static <K> byte getByteValue(final Map<? super K, ?> map, final K key, byte defaultValue) {
        Byte byteObject = getByte(map, key);
        if (byteObject == null) {
            return defaultValue;
        }
        return byteObject.byteValue();
    }