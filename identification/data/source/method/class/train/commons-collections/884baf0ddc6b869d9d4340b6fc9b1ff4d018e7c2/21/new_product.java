public static <K> short getShortValue(final Map<? super K, ?> map, final K key) {
        return applyDefaultValue(map, key, MapUtils::getShort, 0).shortValue();
    }