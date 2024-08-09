public boolean deleteRecursively() {
        if (!isSymbolicLink() && isDirectory()) {
            for (UnixPath path : listContentsOfDirectory()) {
                path.deleteRecursively();
            }
        }
        return uncheck(() -> Files.deleteIfExists(path));
    }