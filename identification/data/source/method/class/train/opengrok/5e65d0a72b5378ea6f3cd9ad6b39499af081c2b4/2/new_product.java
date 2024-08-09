public static String breadcrumbPath(String urlPrefix, String path,
        char sep, String urlPostfix, boolean compact, boolean isDir)
    {
        if (path == null || path.length() == 0) {
            return path;
        }
        String[] pnames = normalize(path.split(escapeForRegex(sep)), compact);
        if (pnames.length == 0) {
            return path;
        }
        String prefix = urlPrefix != null ? urlPrefix : "";
        String postfix = urlPostfix != null ? urlPostfix : "";
        StringBuilder pwd = new StringBuilder(path.length() + pnames.length);
        StringBuilder markup =
            new StringBuilder( (pnames.length + 3 >> 1) * path.length()
                + pnames.length
                * (17 + prefix.length() + postfix.length()));
        int k = path.indexOf(pnames[0]);
        if (path.lastIndexOf(sep, k) != -1) {
            pwd.append('/');
            markup.append(sep);
        }
        for (int i = 0; i < pnames.length; i++ ) {
            pwd.append(URIEncodePath(pnames[i]));
            if (isDir || i < pnames.length - 1) {
                pwd.append('/');
            }
            markup.append(anchorLinkStart).append(prefix).append(pwd)
                .append(postfix).append(closeQuotedTag).append(pnames[i])
                .append(anchorEnd);
            if (isDir || i < pnames.length - 1) {
                markup.append(sep);
            }
        }
        return markup.toString();
    }