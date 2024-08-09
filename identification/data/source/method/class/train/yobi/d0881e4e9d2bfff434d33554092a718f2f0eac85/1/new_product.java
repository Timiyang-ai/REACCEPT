public static String create(List<String> pathSegments, String defaultHostport) {
        return create(join(pathSegments), defaultHostport);
    }