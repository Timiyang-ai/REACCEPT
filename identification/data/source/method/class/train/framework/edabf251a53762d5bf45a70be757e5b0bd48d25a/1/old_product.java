protected <T> Converter<String, T> findConverterFor(
            Class<? extends T> sourceType) {
        return findConverterFor(sourceType, false);
    }