public static <K> double getDoubleValue(final Map<? super K, ?> map, final K key) {
        return applyDefaultValue(map, key, MapUtils::getDouble, 0d).doubleValue();
    }