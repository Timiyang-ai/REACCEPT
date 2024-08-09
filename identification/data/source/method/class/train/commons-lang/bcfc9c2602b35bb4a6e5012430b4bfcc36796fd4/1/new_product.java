public static boolean isAssignable(Class<?>[] classArray, Class<?>[] toClassArray) {
        return isAssignable(classArray, toClassArray, SystemUtils.isJavaVersionAtLeast(1.5f));
    }