@SuppressWarnings("unchecked")
    protected <T> Converter<String, T> findConverterFor(
            Class<? extends T> sourceType, boolean strict) {
        if (sourceType.isEnum()) {
            // enums can be read in lowercase
            return new DesignToStringConverter<T>(sourceType) {

                @Override
                public T convertToModel(String value,
                        Class<? extends T> targetType, Locale locale)
                        throws Converter.ConversionException {
                    return super.convertToModel(value.toUpperCase(),
                            targetType, locale);
                }
            };
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