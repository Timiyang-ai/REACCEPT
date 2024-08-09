public static <K> short getShortValue(final Map<? super K, ?> map, final K key, final short defaultValue) {
        return applyDefaultValue(map, key, MapUtils::getShort, defaultValue).shortValue();
    }