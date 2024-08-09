private static void leaveBreadcrumb(
            String urlPrefix, char sep, String urlPostfix, StringBuilder hyperl,
            String[] path, int index) {
        // Only generate the link if the path element is non-empty. Empty
        // path elements could occur if the path contains two consecutive
        // separator characters, or if the path begins or ends with a path
        // separator.
        if (path[index].length() > 0) {
            hyperl.append(anchorLinkStart).append(urlPrefix);
            appendPath(path, index, hyperl);
            hyperl.append(urlPostfix).append(closeQuotedTag).
                    append(path[index]).append(anchorEnd);
        }
        // Add a separator between each path element, but not after the last
        // one. If the original path ended with a separator, the last element
        // of the path array is an empty string, which means that the final
        // separator will be printed.
        if (index < path.length - 1) {
            hyperl.append(sep);
        }
    }