@NotNull
    public static String getLastPathElement(String path) {
        if (isEmpty(path)) {
            return "";
        }

        if (!path.contains(TAPESTRY_PATH_SEPARATOR)) {
            return path;
        }

        return path.substring(path.lastIndexOf(TAPESTRY_PATH_SEPARATOR) + 1);
    }