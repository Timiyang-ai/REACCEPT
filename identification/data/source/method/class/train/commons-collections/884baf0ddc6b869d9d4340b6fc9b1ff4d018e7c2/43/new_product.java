public static <K> long getLongValue(final Map<? super K, ?> map, final K key, final long defaultValue) {
        return applyDefaultValue(map, key, MapUtils::getLong, defaultValue).longValue();
    }