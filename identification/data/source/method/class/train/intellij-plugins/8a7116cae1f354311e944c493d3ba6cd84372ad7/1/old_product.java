public static String getLastPathElement(String path) {
        if (isEmpty(path)) {
            return "";
        }

        if (path.indexOf(TAPESTRY_PATH_SEPARATOR) == -1) {
            return path;
        }

        return path.substring(path.lastIndexOf(TAPESTRY_PATH_SEPARATOR) + 1);
    }