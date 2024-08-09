public static String create(List<String> pathSegments, String defaultHostport, String defaultScheme) {
        return create(join(pathSegments), defaultHostport, defaultScheme);
    }