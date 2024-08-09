public static String packageIntoPath(String packageName, boolean includeFinalSeparator) {
        if (isEmpty(packageName)) {
            return "";
        }

        return new StringBuilder().append(packageName.replace(PACKAGE_SEPARATOR, TAPESTRY_PATH_SEPARATOR)).append(includeFinalSeparator ? TAPESTRY_PATH_SEPARATOR : "").toString();
    }