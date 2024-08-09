public static <K> float getFloatValue(final Map<? super K, ?> map, final K key) {
        return applyDefaultValue(map, key, MapUtils::getFloat, 0f).floatValue();
    }