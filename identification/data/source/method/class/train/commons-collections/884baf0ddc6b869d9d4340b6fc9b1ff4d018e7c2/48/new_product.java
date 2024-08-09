public static <K> short getShortValue(final Map<? super K, ?> map, final K key, short defaultValue) {
        Short shortObject = getShort(map, key);
        if (shortObject == null) {
            return defaultValue;
        }
        return shortObject.shortValue();
    }