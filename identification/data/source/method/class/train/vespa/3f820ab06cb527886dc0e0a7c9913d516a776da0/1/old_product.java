public boolean deleteRecursively() {
        if (!isSymbolicSymlink() && isDirectory()) {
            for (UnixPath path : listContentsOfDirectory()) {
                path.deleteRecursively();
            }
        }
        return uncheck(() -> Files.deleteIfExists(path));
    }