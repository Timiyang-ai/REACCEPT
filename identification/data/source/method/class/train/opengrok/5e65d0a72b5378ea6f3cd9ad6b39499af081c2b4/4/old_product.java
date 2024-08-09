public static String breadcrumbPath(
            String urlPrefix, String l, char sep, String urlPostfix,
            boolean compact) {
        if (l == null || l.length() <= 1) {
            return l;
        }
        StringBuilder hyperl = new StringBuilder(20);
        String[] path = l.split(escapeForRegex(sep), -1);
        for (int i = 0; i < path.length; i++) {
            leaveBreadcrumb(
                    urlPrefix, sep, urlPostfix, compact, hyperl, path, i);
        }
        return hyperl.toString();
    }