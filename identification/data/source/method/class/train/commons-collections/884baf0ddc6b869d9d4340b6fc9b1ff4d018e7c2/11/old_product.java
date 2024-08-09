public static float getFloatValue(final Map map, final Object key, float defaultValue) {
        Float floatObject = getFloat(map, key);
        if (floatObject == null) {
            return defaultValue;
        }
        return floatObject.floatValue();
    }