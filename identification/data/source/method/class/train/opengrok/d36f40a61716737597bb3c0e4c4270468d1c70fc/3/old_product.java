public static String URIEncode(String q) {
        try {
            // We should probably use an encoding which supports a larger
            // character set, but use ISO-8859-1 for now, since that's what
            // we use other places in the code.
            return URLEncoder.encode(q, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            // Should not happen. ISO-8859-1 must be supported by all JVMs.
            return null;
        }
    }