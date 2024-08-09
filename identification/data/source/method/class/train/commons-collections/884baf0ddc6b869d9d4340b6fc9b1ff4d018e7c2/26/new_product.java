public static <K> boolean getBooleanValue(final Map<? super K, ?> map, final K key, final boolean defaultValue) {
        return applyDefaultValue(map, key, MapUtils::getBoolean, defaultValue).booleanValue();
    }