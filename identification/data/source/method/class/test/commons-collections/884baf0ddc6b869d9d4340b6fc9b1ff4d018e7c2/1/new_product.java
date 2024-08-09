public static <K> int getIntValue(final Map<? super K, ?> map, final K key) {
        return applyDefaultValue(map, key, MapUtils::getInteger, 0).intValue();
    }