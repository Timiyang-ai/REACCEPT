public static String create(
            String relativePath, String defaultHostport, String defaultScheme) {
        return Config.getScheme(defaultScheme) + "://" + Config.getHostport(defaultHostport) +
                relativePath;
    }