public static String getPackageCanonicalName(String canonicalName) {
        return ClassUtils.getPackageName(getCanonicalName(canonicalName));
    }