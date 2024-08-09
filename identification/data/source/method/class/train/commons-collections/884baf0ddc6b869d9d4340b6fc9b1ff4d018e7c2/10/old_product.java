public static long getLongValue(final Map map, final Object key, long defaultValue) {
        Long longObject = getLong(map, key);
        if (longObject == null) {
            return defaultValue;
        }
        return longObject.longValue();
    }