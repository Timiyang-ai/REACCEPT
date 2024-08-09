public static boolean isAssignable(Class<?>[] classArray, Class<?>[] toClassArray) {
        return isAssignable(classArray, toClassArray, SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_5));
    }