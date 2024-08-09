public static String create(String relativePath, String defaultHostport) {
        return create(relativePath, defaultHostport, Config.getScheme());
    }