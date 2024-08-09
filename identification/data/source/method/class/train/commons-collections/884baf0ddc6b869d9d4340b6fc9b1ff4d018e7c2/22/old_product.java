public static byte getByteValue(final Map map, final Object key) {
        Byte byteObject = getByte(map, key);
        if (byteObject == null) {
            return 0;
        }
        return byteObject.byteValue();
    }