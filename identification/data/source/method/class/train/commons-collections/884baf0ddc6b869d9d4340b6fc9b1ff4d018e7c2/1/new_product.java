public static <K> long getLongValue(final Map<? super K, ?> map, final K key) {
        return applyDefaultValue(map, key, MapUtils::getLong, 0L).longValue();
    }