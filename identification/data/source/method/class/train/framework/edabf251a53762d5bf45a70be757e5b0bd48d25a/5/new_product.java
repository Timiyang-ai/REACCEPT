public <T> String format(T object, Class<? extends T> type) {
        if (object == null) {
            return null;
        } else {
            Converter<String, Object> converter = findConverterFor(
                    object.getClass());
            return converter.convertToPresentation(object, null);
        }
    }