@SuppressWarnings("unchecked")
    protected <T> LegacyConverter<String, T> findConverterFor(
            Class<? extends T> sourceType, boolean strict) {
        if (sourceType == Object.class) {
            // Use for propertyIds, itemIds and such. Only string type objects
            // are really supported if no special logic is implemented in the
            // component.
            return (LegacyConverter<String, T>) stringObjectConverter;
        }

        if (converterMap.containsKey(sourceType)) {
            return ((LegacyConverter<String, T>) converterMap.get(sourceType));
        } else if (!strict) {
            for (Class<?> supported : converterMap.keySet()) {
                if (supported.isAssignableFrom(sourceType)) {
                    return ((LegacyConverter<String, T>) converterMap
                            .get(supported));
                }
            }
        }

        if (sourceType.isEnum()) {
            return (LegacyConverter<String, T>) stringEnumConverter;
        }
        return null;
    }