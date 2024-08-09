public static <K> double getDoubleValue(final Map<? super K, ?> map, final K key, final double defaultValue) {
        return applyDefaultValue(map, key, MapUtils::getDouble, defaultValue).doubleValue();
    }