private Path path(String p, String... more) {
        return cacheDir.getFileSystem().getPath(p, more);
    }