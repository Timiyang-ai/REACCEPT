public static <K> float getFloatValue(final Map<? super K, ?> map, final K key) {
        Float floatObject = getFloat(map, key);
        if (floatObject == null) {
            return 0f;
        }
        return floatObject.floatValue();
    }