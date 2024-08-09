public static <K> short getShortValue(final Map<? super K, ?> map, final K key, final short defaultValue) {
        final Short shortObject = getShort(map, key);
        if (shortObject == null) {
            return defaultValue;
        }
        return shortObject.shortValue();
    }