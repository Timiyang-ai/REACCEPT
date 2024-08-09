public static boolean isInnerClass(Class cls) {
        if (cls == null) {
            throw new IllegalArgumentException("The class must not be null");
        }
        return (cls.getName().indexOf(INNER_CLASS_SEPARATOR_CHAR) >= 0);
    }