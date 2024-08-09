public static boolean getBooleanValue(final Map map, final Object key) {
        Boolean booleanObject = getBoolean(map, key);
        if (booleanObject == null) {
            return false;
        }
        return booleanObject.booleanValue();
    }