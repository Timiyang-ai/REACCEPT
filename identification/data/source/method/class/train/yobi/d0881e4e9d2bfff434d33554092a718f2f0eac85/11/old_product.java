public static String create(
            String relativePath, String defaultHostport, String defaultScheme)
            throws MalformedURLException {
        return new URL(
                Config.getScheme(defaultScheme), Config.getHostport(defaultHostport), relativePath)
                .toString();
    }