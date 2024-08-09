public <T> String format(T object, Class<? extends T> type) {
        if (object == null) {
            return null;
        } else {
            return findConverterFor(object.getClass()).convertToPresentation(
                    object, String.class, null);
        }
    }