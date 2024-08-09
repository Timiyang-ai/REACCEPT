public static <K> byte getByteValue(final Map<? super K, ?> map, final K key) {
        return applyDefaultValue(map, key, MapUtils::getByte, 0).byteValue();
    }