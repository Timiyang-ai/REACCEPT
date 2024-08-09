public static boolean isAssignable(Class<?> cls, Class<?> toClass) {
        return isAssignable(cls, toClass, SystemUtils.isJavaVersionAtLeast(1.5f));
    }