public static String create(String relativePath, String defaultHostport) throws MalformedURLException {
        return create(relativePath, defaultHostport, Config.getScheme());
    }