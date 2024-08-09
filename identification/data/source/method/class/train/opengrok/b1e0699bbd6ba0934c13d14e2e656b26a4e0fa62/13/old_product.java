public static String htmlize(CharSequence q) {
        StringBuilder sb = new StringBuilder(q.length() * 2);
        try {
            htmlize(q, sb);
        } catch (IOException ioe) {
            // IOException cannot happen when the destination is a
            // StringBuilder. Wrap in an AssertionError so that callers
            // don't have to check for an IOException that should never
            // happen.
            throw new AssertionError("StringBuilder threw IOException", ioe);
        }
        return sb.toString();
    }