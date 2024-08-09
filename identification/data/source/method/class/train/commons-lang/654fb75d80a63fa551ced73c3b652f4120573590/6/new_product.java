public static String getShortCanonicalName(final String canonicalName) {
        return ClassUtils.getShortClassName(getCanonicalName(canonicalName));
    }