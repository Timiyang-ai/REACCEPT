public static String getSimpleName(final Object object, final String valueIfNull) {
        if (object == null) {
            return valueIfNull;
        }
        return getSimpleName(object.getClass());
    }