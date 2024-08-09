public static float getFloatValue(final Map map, final Object key) {
        Float floatObject = getFloat(map, key);
        if (floatObject == null) {
            return 0f;
        }
        return floatObject.floatValue();
    }