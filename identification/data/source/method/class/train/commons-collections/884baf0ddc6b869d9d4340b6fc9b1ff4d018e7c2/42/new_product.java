public static <K> int getIntValue(final Map<? super K, ?> map, final K key) {
        Integer integerObject = getInteger(map, key);
        if (integerObject == null) {
            return 0;
        }
        return integerObject.intValue();
    }