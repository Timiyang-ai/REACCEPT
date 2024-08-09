public static String create(String relativePath) throws MalformedURLException {
        return create(relativePath, Config.getHostport());
    }