public <T> String format(T object, Class<? extends T> type) {
        if (object == null) {
            return null;
        } else {
            LegacyConverter<String, Object> converter = findConverterFor(
                    object.getClass());
            return converter.convertToPresentation(object, String.class, null);
        }
    }