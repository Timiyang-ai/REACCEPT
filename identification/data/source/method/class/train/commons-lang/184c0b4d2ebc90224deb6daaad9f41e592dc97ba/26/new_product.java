public static String getPackageName(String className) {
        if (className == null) {
            return StringUtils.EMPTY;
        }
        int i = className.lastIndexOf(PACKAGE_SEPARATOR_CHAR);
        if (i == -1) {
            return StringUtils.EMPTY;
        }
        return className.substring(0, i);
    }