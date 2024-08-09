public static String create(List<String> pathSegments, String defaultHostport, String defaultScheme) {
        String path = "/" + StringUtils.join(pathSegments, "/");
        return Config.getScheme(defaultScheme) + "://" + Config.getHostport(defaultHostport) + path;
    }