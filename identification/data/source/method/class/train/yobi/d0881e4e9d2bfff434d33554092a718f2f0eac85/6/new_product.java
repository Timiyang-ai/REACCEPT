public static String create(List<String> pathSegments, String defaultHostport) throws MalformedURLException {
        return create(join(pathSegments), defaultHostport);
    }