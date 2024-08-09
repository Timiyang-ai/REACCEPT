public static String getPackageCanonicalName(final String canonicalName) {
        return ClassUtils.getPackageName(getCanonicalName(canonicalName));
    }