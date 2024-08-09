public static <K> byte getByteValue(final Map<? super K, ?> map, final K key, final byte defaultValue) {
        return applyDefaultValue(map, key, MapUtils::getByte, defaultValue).byteValue();
    }