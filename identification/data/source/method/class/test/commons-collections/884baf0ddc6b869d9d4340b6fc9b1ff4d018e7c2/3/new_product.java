public static <K> byte getByteValue(final Map<? super K, ?> map, final K key, final byte defaultValue) {
        final Byte byteObject = getByte(map, key);
        if (byteObject == null) {
            return defaultValue;
        }
        return byteObject.byteValue();
    }