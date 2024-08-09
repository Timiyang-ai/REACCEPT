public static <K> long getLongValue(final Map<? super K, ?> map, final K key) {
        Long longObject = getLong(map, key);
        if (longObject == null) {
            return 0L;
        }
        return longObject.longValue();
    }