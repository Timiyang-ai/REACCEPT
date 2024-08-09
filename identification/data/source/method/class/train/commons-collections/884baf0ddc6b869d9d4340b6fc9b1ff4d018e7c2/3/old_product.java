public static boolean getBooleanValue(final Map map, final Object key, boolean defaultValue) {
        Boolean booleanObject = getBoolean(map, key);
        if (booleanObject == null) {
            return defaultValue;
        }
        return booleanObject.booleanValue();
    }