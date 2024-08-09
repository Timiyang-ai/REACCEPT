public static String getPackageName(Class cls) {
        if (cls == null) {
            throw new IllegalArgumentException("The class must not be null");
        }
        return getPackageName(cls.getName());
    }