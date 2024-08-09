public static String getPackageCanonicalName(Class cls) {
        if (cls == null) {
            return StringUtils.EMPTY;
        }
        return getPackageCanonicalName(cls.getName());
    }