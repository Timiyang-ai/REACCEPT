public static <K> int getIntValue(final Map<? super K, ?> map, final K key, final int defaultValue) {
        return applyDefaultValue(map, key, MapUtils::getInteger, defaultValue).intValue();
    }