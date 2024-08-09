public static String getShortClassName(Class cls) {
        if (cls == null) {
            throw new IllegalArgumentException("The class must not be null");
        }
        return getShortClassName(cls.getName());
    }