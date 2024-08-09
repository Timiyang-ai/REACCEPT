public static String breadcrumbPath(String urlPrefix, String path,
        char sep, String urlPostfix, boolean compact)
    {
        if (path == null || path.length() == 0) {
            return path;
        }
        return breadcrumbPath(urlPrefix, path, sep, urlPostfix, compact,
            path.charAt(path.length() - 1) == sep);
    }