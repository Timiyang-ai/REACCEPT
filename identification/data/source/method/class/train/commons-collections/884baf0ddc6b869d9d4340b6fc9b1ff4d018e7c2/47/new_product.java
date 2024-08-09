public static <K> double getDoubleValue(final Map<? super K, ?> map, final K key, final double defaultValue) {
        final Double doubleObject = getDouble(map, key);
        if (doubleObject == null) {
            return defaultValue;
        }
        return doubleObject.doubleValue();
    }