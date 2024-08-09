public static <K> double getDoubleValue(final Map<? super K, ?> map, final K key) {
        Double doubleObject = getDouble(map, key);
        if (doubleObject == null) {
            return 0d;
        }
        return doubleObject.doubleValue();
    }