protected <T> LegacyConverter<String, T> findConverterFor(
            Class<? extends T> sourceType) {
        return findConverterFor(sourceType, false);
    }