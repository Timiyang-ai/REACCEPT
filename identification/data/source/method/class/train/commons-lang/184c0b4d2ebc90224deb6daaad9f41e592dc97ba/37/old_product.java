public static String getPackageName(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("The object must not be null");
        }
        return getPackageName(object.getClass().getName());
    }