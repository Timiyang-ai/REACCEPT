@SuppressWarnings("unchecked")
    protected <T> Converter<String, T> findConverterFor(
            Class<? extends T> sourceType, boolean strict) {
        if (sourceType == Object.class) {
            // Use for propertyIds, itemIds and such. Only string type objects
            // are really supported if no special logic is implemented in the
            // component.
            return (Converter<String, T>) stringObjectConverter;
        }

        if (converterMap.containsKey(sourceType)) {
            return ((Converter<String, T>) converterMap.get(sourceType));
        } else if (!strict) {
            for (Class<?> supported : converterMap.keySet()) {
                if (supported.isAssignableFrom(sourceType)) {
                    return ((Converter<String, T>) converterMap
                            .get(supported));
                }
            }
        }

        if (sourceType.isEnum()) {
            return (Converter<String, T>) stringEnumConverter;
        }
        return null;
    }