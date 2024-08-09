public static <K> int getIntValue(final Map<? super K, ?> map, final K key, int defaultValue) {
        Integer integerObject = getInteger(map, key);
        if (integerObject == null) {
            return defaultValue;
        }
        return integerObject.intValue();
    }