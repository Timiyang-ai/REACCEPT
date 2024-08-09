public static <K> float getFloatValue(final Map<? super K, ?> map, final K key) {
        final Float floatObject = getFloat(map, key);
        if (floatObject == null) {
            return 0f;
        }
        return floatObject.floatValue();
    }