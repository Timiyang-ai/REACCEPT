public static String getCanonicalName(final Class<?> cls, final String valueIfNull) {
        return cls == null ? valueIfNull : cls.getClass().getCanonicalName();
    }