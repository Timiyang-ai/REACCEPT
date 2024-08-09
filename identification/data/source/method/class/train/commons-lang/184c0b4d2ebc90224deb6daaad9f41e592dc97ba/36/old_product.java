public static String getShortClassName(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("The object must not be null");
        }
        return getShortClassName(object.getClass().getName());
    }