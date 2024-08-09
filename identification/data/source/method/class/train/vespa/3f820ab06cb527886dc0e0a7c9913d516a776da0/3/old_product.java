public boolean deleteRecursively() {
        if (isDirectory()) {
            for (UnixPath path : listContentsOfDirectory()) {
                path.deleteRecursively();
            }
        }

        return uncheck(() -> Files.deleteIfExists(path));
    }