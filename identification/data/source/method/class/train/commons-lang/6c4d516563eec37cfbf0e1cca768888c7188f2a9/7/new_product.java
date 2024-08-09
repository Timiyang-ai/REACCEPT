public static boolean isInnerClass(Class cls) {
        if (cls == null) {
            return false;
        }
        return cls.getName().indexOf(INNER_CLASS_SEPARATOR_CHAR) >= 0;
    }