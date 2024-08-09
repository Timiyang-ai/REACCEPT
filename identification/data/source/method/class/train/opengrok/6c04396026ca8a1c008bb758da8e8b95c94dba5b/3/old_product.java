public static String URIEncodePath(String path) {
        try {
            URI uri = new URI(null, null, path, null);
            return uri.getRawPath();
        } catch (URISyntaxException ex) {
            OpenGrokLogger.getLogger().log(Level.WARNING,
                "Could not encode path " + path, ex);
        }
        return "";
    }