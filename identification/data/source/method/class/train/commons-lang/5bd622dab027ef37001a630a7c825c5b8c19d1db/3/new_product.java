public static String getSimpleName(final Class<?> cls) {
        if (cls == null) {
            return StringUtils.EMPTY;
        }
        return cls.getSimpleName();
    }