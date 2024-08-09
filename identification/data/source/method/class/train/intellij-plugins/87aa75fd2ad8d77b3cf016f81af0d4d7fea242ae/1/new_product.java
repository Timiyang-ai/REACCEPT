public static String packageIntoPath(String packageName, boolean includeFinalSeparator) {
        if (isEmpty(packageName)) {
            return "";
        }

        return packageName.replace(PACKAGE_SEPARATOR, TAPESTRY_PATH_SEPARATOR) + (includeFinalSeparator ? TAPESTRY_PATH_SEPARATOR : "");
    }