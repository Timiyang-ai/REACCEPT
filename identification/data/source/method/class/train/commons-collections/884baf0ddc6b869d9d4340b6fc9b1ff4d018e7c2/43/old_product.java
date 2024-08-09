public static <K> long getLongValue(final Map<? super K, ?> map, final K key, final long defaultValue) {
        final Long longObject = getLong(map, key);
        if (longObject == null) {
            return defaultValue;
        }
        return longObject.longValue();
    }