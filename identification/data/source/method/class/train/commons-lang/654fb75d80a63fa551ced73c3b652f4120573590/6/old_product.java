public static String getShortCanonicalName(String canonicalName) {
        return ClassUtils.getShortClassName(getCanonicalName(canonicalName));
    }