public static String getCanonicalName(final Object object, final String valueIfNull) {
        return object == null ? valueIfNull : object.getClass().getCanonicalName();
    }