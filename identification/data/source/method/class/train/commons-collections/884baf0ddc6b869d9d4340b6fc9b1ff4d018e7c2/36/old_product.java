public static double getDoubleValue(final Map map, final Object key) {
        Double doubleObject = getDouble(map, key);
        if (doubleObject == null) {
            return 0d;
        }
        return doubleObject.doubleValue();
    }