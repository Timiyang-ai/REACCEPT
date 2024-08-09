public static String breadcrumbPath(String urlPrefix, String l, char sep) {
        if (l == null || l.length() <= 1) {
            return l;
        }
        StringBuilder hyperl = new StringBuilder(20);
        if (l.charAt(0) == sep) {
            hyperl.append(sep);
        }
        int s = 0,
         e = 0;
        while ((e = l.indexOf(sep, s)) >= 0) {
            if (e - s > 0) {
                hyperl.append(anchorLinkStart);
                hyperl.append(urlPrefix);
                hyperl.append(l.substring(0, e));
                hyperl.append("/");
                hyperl.append(closeQuotedTag);
                hyperl.append(l.substring(s, e));
                hyperl.append(anchorEnd);
                hyperl.append(sep);
            }
            s = e + 1;
        }
        if (s < l.length()) {
            hyperl.append(anchorLinkStart);
            hyperl.append(urlPrefix);
            hyperl.append(l);
            hyperl.append(closeQuotedTag);
            hyperl.append(l.substring(s, l.length()));
            hyperl.append(anchorEnd);
        }
        return hyperl.toString();
    }