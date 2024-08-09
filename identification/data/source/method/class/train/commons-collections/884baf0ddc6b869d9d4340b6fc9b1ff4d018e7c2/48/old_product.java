public static short getShortValue(final Map map, final Object key, short defaultValue) {
        Short shortObject = getShort(map, key);
        if (shortObject == null) {
            return defaultValue;
        }
        return shortObject.shortValue();
    }