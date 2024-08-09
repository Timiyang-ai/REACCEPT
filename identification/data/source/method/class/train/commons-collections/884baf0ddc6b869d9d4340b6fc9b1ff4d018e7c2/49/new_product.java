public static <K> float getFloatValue(final Map<? super K, ?> map, final K key, final float defaultValue) {
        return applyDefaultValue(map, key, MapUtils::getFloat, defaultValue).floatValue();
    }