@SuppressWarnings("unchecked")
    protected <T> Converter<String, T> findConverterFor(
            Class<? extends T> sourceType, boolean strict) {
        if (sourceType.isEnum()) {
            return (Converter<String, T>) stringEnumConverter;
        } else if (converterMap.containsKey(sourceType)) {
            return ((Converter<String, T>) converterMap.get(sourceType));
        } else if (!strict) {
            for (Class<?> supported : converterMap.keySet()) {
                if (supported.isAssignableFrom(sourceType)) {
                    return ((Converter<String, T>) converterMap.get(supported));
                }
            }
        }
        return null;
    }