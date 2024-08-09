public static String getSimpleName(Class<?> cls) {
        if (cls == null) {
            return StringUtils.EMPTY;
        }
        return cls.getSimpleName();
    }