public static String create(List<String> pathSegments) throws MalformedURLException {
        return create(join(pathSegments));
    }