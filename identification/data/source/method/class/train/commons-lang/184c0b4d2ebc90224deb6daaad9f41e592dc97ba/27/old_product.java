public static String getPackageName(Class<?> cls) {
        if (cls == null) {
            return StringUtils.EMPTY;
        }
        return cls.getPackage().getName();
    }