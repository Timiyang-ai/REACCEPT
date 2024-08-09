public static String htmlize(String s) {
        if (!needsHtmlize(s, false)) return s;

        StringBuilder sb = new StringBuilder(s.length() * 2);
        try {
            htmlize(s, sb, false);
        } catch (IOException ioe) {
            // IOException cannot happen when the destination is a
            // StringBuilder. Wrap in an AssertionError so that callers
            // don't have to check for an IOException that should never
            // happen.
            throw new AssertionError("StringBuilder threw IOException", ioe);
        }
        return sb.toString();
    }