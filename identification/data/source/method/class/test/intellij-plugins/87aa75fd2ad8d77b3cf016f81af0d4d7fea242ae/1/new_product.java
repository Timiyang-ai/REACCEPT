public static String pathIntoPackage(String path, boolean removeLastElement) {
        if (isEmpty(path)) {
            return "";
        }

        if (path.endsWith(TAPESTRY_PATH_SEPARATOR)) {
            path = path.substring(0, path.length() - 1);
        }

        if (path.startsWith(TAPESTRY_PATH_SEPARATOR)) {
            path = path.substring(1);
        }

        if (removeLastElement && path.lastIndexOf(TAPESTRY_PATH_SEPARATOR) != -1) {
            path = path.substring(0, path.lastIndexOf(TAPESTRY_PATH_SEPARATOR));
        }

        return path.replace(TAPESTRY_PATH_SEPARATOR, PACKAGE_SEPARATOR);
    }