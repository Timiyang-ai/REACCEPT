public static <K> float getFloatValue(final Map<? super K, ?> map, final K key, float defaultValue) {
        Float floatObject = getFloat(map, key);
        if (floatObject == null) {
            return defaultValue;
        }
        return floatObject.floatValue();
    }