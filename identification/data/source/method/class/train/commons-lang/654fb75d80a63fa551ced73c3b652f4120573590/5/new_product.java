public static String getPackageCanonicalName(final String canonicalName) {
        return getPackageName(getCanonicalName(canonicalName));
    }