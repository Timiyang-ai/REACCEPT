public static String getFirstPathElement(String path) {
        if (isEmpty(path)) {
            return "";
        }

        if (!path.contains(TAPESTRY_PATH_SEPARATOR)) {
            return path;
        }

        if (path.startsWith(TAPESTRY_PATH_SEPARATOR)) {
            path = path.substring(1);
        }

        return path.substring(0, path.indexOf(TAPESTRY_PATH_SEPARATOR));
    }