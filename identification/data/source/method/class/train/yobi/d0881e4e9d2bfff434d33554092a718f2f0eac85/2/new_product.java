public static String create(List<String> pathSegments, String defaultHostport, String defaultScheme) throws MalformedURLException {
        return create(join(pathSegments), defaultHostport, defaultScheme);
    }