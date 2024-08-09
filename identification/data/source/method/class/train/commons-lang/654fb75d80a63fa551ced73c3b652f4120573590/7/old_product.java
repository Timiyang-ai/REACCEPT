public static String getShortCanonicalName(Class cls) {
        if (cls == null) {
            return StringUtils.EMPTY;
        }
        return getShortCanonicalName(cls.getName());
    }