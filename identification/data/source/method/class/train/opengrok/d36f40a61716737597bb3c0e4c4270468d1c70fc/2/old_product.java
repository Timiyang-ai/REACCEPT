public static String URIEncode(String q) {
        try {
            return q == null ? "" : URLEncoder.encode(q, UTF8.name());
        } catch (UnsupportedEncodingException e) {
            // Should not happen. UTF-8 must be supported by JVMs.
            LOGGER.log(
                    Level.WARNING, "Failed to URL-encode UTF-8: ", e);
        }
        return null;
    }