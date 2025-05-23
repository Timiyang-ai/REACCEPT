public static <K> short getShortValue(final Map<? super K, ?> map, final K key) {
        Short shortObject = getShort(map, key);
        if (shortObject == null) {
            return 0;
        }
        return shortObject.shortValue();
    }