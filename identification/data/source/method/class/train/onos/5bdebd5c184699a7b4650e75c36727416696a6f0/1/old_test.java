    private static DocumentPath path(String path) {
        return DocumentPath.from(path.replace(".", DocumentPath.DEFAULT_SEPARATOR));
    }